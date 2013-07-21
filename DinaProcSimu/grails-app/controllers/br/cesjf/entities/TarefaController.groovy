package br.cesjf.entities

import org.springframework.dao.DataIntegrityViolationException

class TarefaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tarefaInstanceList: Tarefa.list(params), tarefaInstanceTotal: Tarefa.count()]
    }

    def create() {
        [tarefaInstance: new Tarefa(params)]
    }

    def save() {
        def tarefaInstance = new Tarefa(params)
        if (!tarefaInstance.save(flush: true)) {
            render(view: "create", model: [tarefaInstance: tarefaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), tarefaInstance.id])
        redirect(action: "show", id: tarefaInstance.id)
    }

    def show(Long id) {
        def tarefaInstance = Tarefa.get(id)
        if (!tarefaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), id])
            redirect(action: "list")
            return
        }

        [tarefaInstance: tarefaInstance]
    }

    def edit(Long id) {
        def tarefaInstance = Tarefa.get(id)
        if (!tarefaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), id])
            redirect(action: "list")
            return
        }

        [tarefaInstance: tarefaInstance]
    }

    def update(Long id, Long version) {
        def tarefaInstance = Tarefa.get(id)
        if (!tarefaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tarefaInstance.version > version) {
                tarefaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tarefa.label', default: 'Tarefa')] as Object[],
                          "Another user has updated this Tarefa while you were editing")
                render(view: "edit", model: [tarefaInstance: tarefaInstance])
                return
            }
        }

        tarefaInstance.properties = params

        if (!tarefaInstance.save(flush: true)) {
            render(view: "edit", model: [tarefaInstance: tarefaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), tarefaInstance.id])
        redirect(action: "show", id: tarefaInstance.id)
    }

    def delete(Long id) {
        def tarefaInstance = Tarefa.get(id)
        if (!tarefaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), id])
            redirect(action: "list")
            return
        }

        try {
            tarefaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tarefa.label', default: 'Tarefa'), id])
            redirect(action: "show", id: id)
        }
    }
}
