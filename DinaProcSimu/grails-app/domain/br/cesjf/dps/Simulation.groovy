package br.cesjf.dps

import br.ufjf.mmc.jynacore.JynaSimulationData
import br.ufjf.mmc.jynacore.metamodel.instance.ClassInstanceItem
import br.ufjf.mmc.jynacore.JynaValued
import br.ufjf.mmc.jynacore.metamodel.instance.MetaModelInstanceStorer
import br.ufjf.mmc.jynacore.metamodel.instance.impl.DefaultMetaModelInstanceStorerJDOM
//import br.ufjf.mmc.jynacore.impl.DefaultSimulationData
import br.ufjf.mmc.jynacore.JynaSimulableModel
import br.ufjf.mmc.jynacore.metamodel.simulator.impl.DefaultMetaModelInstanceEulerMethod
import br.ufjf.mmc.jynacore.JynaSimulationMethod
import br.ufjf.mmc.jynacore.JynaSimulationProfile
import br.ufjf.mmc.jynacore.impl.DefaultSimulationProfile
import br.ufjf.mmc.jynacore.metamodel.simulator.impl.DefaultMetaModelInstanceSimulation
import br.ufjf.mmc.jynacore.JynaSimulation
import org.springframework.web.multipart.commons.CommonsMultipartFile

class Simulation {
    
    File file

    static constraints = {
        file nullable:true
    }
    
    def createSimulation(){
        JynaSimulation simulation = new DefaultMetaModelInstanceSimulation()
        JynaSimulationProfile profile = new DefaultSimulationProfile()
        JynaSimulationMethod method = new DefaultMetaModelInstanceEulerMethod()
        JynaSimulableModel instance
        //DefaultSimulationData data = new DefaultSimulationData()

        MetaModelInstanceStorer storer = new DefaultMetaModelInstanceStorerJDOM()
        String modelFile
        String propName
        String filePrefix
        try {
            modelFile = args[0]
            propName = args[1]
            filePrefix = args[2]
        } catch (Exception e) {
            System.out.println("Usage: " + args[0] + "<metamodelinstance> <property> <prefix data files>\n")
            return
        }
        
        instance = storer.loadFromFile(new File("C:\\Users\\heitor.filho\\teste-exemplo-tcc.jymmi"))
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
        runSimulation(simulation, skip)

        try {
            File file = new File(filePrefix + ".dat")
            FileWriter fw = new FileWriter(file)
            fw.write(data.toString())
            fw.close()

        } catch (Exception e) {
            e.printStackTrace()
        }
        
        
    }
}
