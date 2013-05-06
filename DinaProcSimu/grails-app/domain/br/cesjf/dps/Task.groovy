package br.cesjf.dps

class Task {
	
	String name
        Task parentTask

    static constraints = {
        name size: 5..20, blank: false, unique: true
        parentTask unique:false, blank:true, nullable:true
        
    }
	
	static hasMany = [developers: Developer]
	
	
}
