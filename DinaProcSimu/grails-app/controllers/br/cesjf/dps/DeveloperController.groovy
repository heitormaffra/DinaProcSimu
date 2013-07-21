package br.cesjf.dps

import org.springframework.dao.DataIntegrityViolationException

class DeveloperController {
    
    def developers = []

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    //    def list(Integer max) {
    //        params.max = Math.min(max ?: 10, 100)
    //        [developerInstanceList: Developer.list(params), developerInstanceTotal: Developer.count()]
    //    }
    
    def list() {
        [developerInstanceList: developers, developerInstanceTotal: 10]
        
        
    }

    def create() {
        [developerInstance: new Developer(params)]
    }

    def save() {
        def developerInstance = new Developer(params)
        if (!developerInstance.save(flush: true)) {
            render(view: "create", model: [developerInstance: developerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'developer.label', default: 'Developer'), developerInstance.id])
        redirect(action: "show", id: developerInstance.id)
    }

    def show(Long id) {
        def developerInstance = Developer.get(id)
        if (!developerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'developer.label', default: 'Developer'), id])
            redirect(action: "list")
            return
        }

        [developerInstance: developerInstance]
    }

    def edit(Long id) {
        def developerInstance = Developer.get(id)
        if (!developerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'developer.label', default: 'Developer'), id])
            redirect(action: "list")
            return
        }

        [developerInstance: developerInstance]
    }

    def update(Long id, Long version) {
        def developerInstance = Developer.get(id)
        if (!developerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'developer.label', default: 'Developer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (developerInstance.version > version) {
                developerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'developer.label', default: 'Developer')] as Object[],
                          "Another user has updated this Developer while you were editing")
                render(view: "edit", model: [developerInstance: developerInstance])
                return
            }
        }

        developerInstance.properties = params

        if (!developerInstance.save(flush: true)) {
            render(view: "edit", model: [developerInstance: developerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'developer.label', default: 'Developer'), developerInstance.id])
        redirect(action: "show", id: developerInstance.id)
    }

    def delete(Long id) {
        def developerInstance = Developer.get(id)
        if (!developerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'developer.label', default: 'Developer'), id])
            redirect(action: "list")
            return
        }

        try {
            developerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'developer.label', default: 'Developer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'developer.label', default: 'Developer'), id])
            redirect(action: "show", id: id)
        }
    }
    
    def createDeveloper() {
        def developer = new Developer()
        developer.id = 1
        developer.name = "Heitor"
        developers += developer
        
        developers.each{
            println developer.name
        }
        
        redirect(action: "list")

    }

    def listDevelopers() {
        return developers
    }
}
