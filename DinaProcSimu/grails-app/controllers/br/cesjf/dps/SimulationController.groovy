package br.cesjf.dps

import org.springframework.dao.DataIntegrityViolationException
import br.ufjf.mmc.jynacore.JynaSimulationData
import br.ufjf.mmc.jynacore.metamodel.instance.ClassInstanceItem
import br.ufjf.mmc.jynacore.JynaValued
import br.ufjf.mmc.jynacore.metamodel.instance.MetaModelInstanceStorer
import br.ufjf.mmc.jynacore.metamodel.instance.impl.DefaultMetaModelInstanceStorerJDOM
import br.ufjf.mmc.jynacore.impl.DefaultSimulationData
import br.ufjf.mmc.jynacore.JynaSimulableModel
import br.ufjf.mmc.jynacore.metamodel.simulator.impl.DefaultMetaModelInstanceEulerMethod
import br.ufjf.mmc.jynacore.JynaSimulationMethod
import br.ufjf.mmc.jynacore.JynaSimulationProfile
import br.ufjf.mmc.jynacore.impl.DefaultSimulationProfile
import br.ufjf.mmc.jynacore.metamodel.simulator.impl.DefaultMetaModelInstanceSimulation
import br.ufjf.mmc.jynacore.JynaSimulation
import org.springframework.web.multipart.commons.CommonsMultipartFile

class SimulationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", simulation: "GET"]
    
    Double timeToConclude

    def index() {
        redirect(action: "simulation", params: params)
    }
    
    def simulation(){
        
        JynaSimulation simulation = new DefaultMetaModelInstanceSimulation()
        JynaSimulationProfile profile = new DefaultSimulationProfile()
        JynaSimulationMethod method = new DefaultMetaModelInstanceEulerMethod()
        JynaSimulableModel instance
        DefaultSimulationData data = new DefaultSimulationData()

        MetaModelInstanceStorer storer = new DefaultMetaModelInstanceStorerJDOM()
        String modelFile
        String propName
        String filePrefix
        //        try {
        //            modelFile = args[0]
        //            propName = args[1]
        //            filePrefix = args[2]
        //        } catch (Exception e) {
        //            System.out.println("Usage: " + args[0] + "<metamodelinstance> <property> <prefix data files>\n")
        //            return
        //        }
        
        try{
            def teste = params.file
            
            instance = storer.loadFromFile(new File("C:\\modelo-simples\\Instância de Projeto Simples com Cenários 2.jymmi"))
            profile.setInitialTime(0.0)
            profile.setFinalTime(5.0)
            profile.setTimeLimits(10000, 5.0)
            int skip = 10
    

            simulation.setMethod(method)
            simulation.setProfile(profile)
            data.removeAll()
            data.clearAll()

            for (JynaValued jv : instance.getAllJynaValued()) {
                ClassInstanceItem cii = (ClassInstanceItem) jv
                if (cii.getName().equals(propName)) {
                    data.add(cii.getClassInstance().getName() + "." + cii.getName(), jv)
                }
            }

            simulation.setModel(instance)
        
            simulation.setSimulationData((JynaSimulationData) data)
        
            simulation.reset()

            data.register(0.0)
        
            int steps = simulation.getProfile().getTimeSteps()
        
            for (int i = 0;
                i < steps;
                i++) {
                simulation.step();
                if (i % skip == 0) {
                    simulation.register();
                }
            }
        
            //runSimulation(simulation, skip)

            try {
                File fileSaida = new File(filePrefix + ".dat")
                FileWriter fw = new FileWriter(fileSaida)
                fw.write(data.toString())
                fw.close()

            } catch (Exception e) {
                e.printStackTrace()
            }
        
            //redirect(action:"create")
            Simulation simu = new Simulation()
            simu.id = 1
            simu.timeToConclude = simulation.getProfile().getFinalTime()
        
            def simulationInstance = simu
        
            //[simulationInstanceList: Simulation.list(params), simulationInstanceTotal: Simulation.count()]
            redirect(action: "simulationDone", params:[time: simulation.getProfile().getFinalTime()])
        
            //return simu.id
        }catch(FileNotFoundException e){
            redirect(action:"create")
            flash.message = message(code: 'default.not.found.file', args: [e.getMessage()])
        }
    }
    
    //def simulationDone(){}
    
    def  simulationDone(Long id, Double time){
        def simulationInstance = new Simulation()
        simulationInstance.id = id
        simulationInstance.timeToConclude =  time
        if (!simulationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'simulation.label', default: 'Simulation'), time])
            redirect(action: "list")
            return
        }

        [simulationInstance: simulationInstance]
        //return 
    }
    
    def void runSimulation(JynaSimulation simulation, int skip) throws Exception {
        //simulation.run();
        int steps = simulation.getProfile().getTimeSteps();

        System.out.println("Simulating with "+simulation.getProfile().getTimeSteps()+" iterations. Interval "+simulation.getProfile().getTimeInterval()+" to "+simulation.getProfile().getFinalTime());
        for (int i = 0;
            i < steps;
            i++) {
            simulation.step();
            if (i % skip == 0) {
                simulation.register();
            }
        }
        //System.out.println("Simulating done!");
        
        
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
