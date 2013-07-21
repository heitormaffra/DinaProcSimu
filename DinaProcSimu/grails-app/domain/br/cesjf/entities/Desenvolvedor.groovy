package br.cesjf.entities

class Desenvolvedor {
	
	String nome
	Double experiencia
	
	static hasMany = [tarefas: Tarefa]
	static belongsTo = Tarefa

    static constraints = {
		nome blank:false ,nullable:false
		experiencia unique:true , minSize: 0
    }
	
	String toString(){
		return nome
	}
}
