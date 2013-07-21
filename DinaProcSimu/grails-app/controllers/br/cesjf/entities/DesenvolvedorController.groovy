package br.cesjf.entities

import org.springframework.dao.DataIntegrityViolationException

class DesenvolvedorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [desenvolvedorInstanceList: Desenvolvedor.list(params), desenvolvedorInstanceTotal: Desenvolvedor.count()]
    }

    def create() {
        [desenvolvedorInstance: new Desenvolvedor(params)]
    }

    def save() {
        def desenvolvedorInstance = new Desenvolvedor(params)
        if (!desenvolvedorInstance.save(flush: true)) {
            render(view: "create", model: [desenvolvedorInstance: desenvolvedorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'desenvolvedor.label', default: 'Desenvolvedor'), desenvolvedorInstance.id])
        redirect(action: "show", id: desenvolvedorInstance.id)
    }

    def show(Long id) {
        def desenvolvedorInstance = Desenvolvedor.get(id)
        if (!desenvolvedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'desenvolvedor.label', default: 'Desenvolvedor'), id])
            redirect(action: "list")
            return
        }

        [desenvolvedorInstance: desenvolvedorInstance]
    }

    def edit(Long id) {
        def desenvolvedorInstance = Desenvolvedor.get(id)
        if (!desenvolvedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'desenvolvedor.label', default: 'Desenvolvedor'), id])
            redirect(action: "list")
            return
        }

        [desenvolvedorInstance: desenvolvedorInstance]
    }

    def update(Long id, Long version) {
        def desenvolvedorInstance = Desenvolvedor.get(id)
        if (!desenvolvedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'desenvolvedor.label', default: 'Desenvolvedor'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (desenvolvedorInstance.version > version) {
                desenvolvedorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'desenvolvedor.label', default: 'Desenvolvedor')] as Object[],
                          "Another user has updated this Desenvolvedor while you were editing")
                render(view: "edit", model: [desenvolvedorInstance: desenvolvedorInstance])
                return
            }
        }

        desenvolvedorInstance.properties = params

        if (!desenvolvedorInstance.save(flush: true)) {
            render(view: "edit", model: [desenvolvedorInstance: desenvolvedorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'desenvolvedor.label', default: 'Desenvolvedor'), desenvolvedorInstance.id])
        redirect(action: "show", id: desenvolvedorInstance.id)
    }

    def delete(Long id) {
        def desenvolvedorInstance = Desenvolvedor.get(id)
        if (!desenvolvedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'desenvolvedor.label', default: 'Desenvolvedor'), id])
            redirect(action: "list")
            return
        }

        try {
            desenvolvedorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'desenvolvedor.label', default: 'Desenvolvedor'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'desenvolvedor.label', default: 'Desenvolvedor'), id])
            redirect(action: "show", id: id)
        }
    }
}
