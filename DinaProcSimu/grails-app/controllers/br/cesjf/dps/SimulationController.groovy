package br.cesjf.dps

import org.springframework.dao.DataIntegrityViolationException

class SimulationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "create", params: params)
    }
    
    def exibeArquivo(){
         
        println request.getFile('file').text
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [simulationInstanceList: Simulation.list(params), simulationInstanceTotal: Simulation.count()]
    }

    def create() {
        [simulationInstance: new Simulation(params)]
    }

    def save() {
        def simulationInstance = new Simulation(params)
        
        if (!simulationInstance.save(flush: true)) {
            render(view: "create", model: [simulationInstance: simulationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'simulation.label', default: 'Simulation'), simulationInstance.id])
        redirect(action: "show", id: simulationInstance.id)
    }
    
    def executaSimulacao(){
        Simulation.createSimulation()
    }

    def show(Long id) {
        def simulationInstance = Simulation.get(id)
        if (!simulationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'simulation.label', default: 'Simulation'), id])
            redirect(action: "list")
            return
        }

        [simulationInstance: simulationInstance]
    }

    def edit(Long id) {
        def simulationInstance = Simulation.get(id)
        if (!simulationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'simulation.label', default: 'Simulation'), id])
            redirect(action: "list")
            return
        }

        [simulationInstance: simulationInstance]
    }

    def update(Long id, Long version) {
        def simulationInstance = Simulation.get(id)
        if (!simulationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'simulation.label', default: 'Simulation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (simulationInstance.version > version) {
                simulationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'simulation.label', default: 'Simulation')] as Object[],
                          "Another user has updated this Simulation while you were editing")
                render(view: "edit", model: [simulationInstance: simulationInstance])
                return
            }
        }

        simulationInstance.properties = params

        if (!simulationInstance.save(flush: true)) {
            render(view: "edit", model: [simulationInstance: simulationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'simulation.label', default: 'Simulation'), simulationInstance.id])
        redirect(action: "show", id: simulationInstance.id)
    }

    def delete(Long id) {
        def simulationInstance = Simulation.get(id)
        if (!simulationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'simulation.label', default: 'Simulation'), id])
            redirect(action: "list")
            return
        }

        try {
            simulationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'simulation.label', default: 'Simulation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'simulation.label', default: 'Simulation'), id])
            redirect(action: "show", id: id)
        }
    }
}
