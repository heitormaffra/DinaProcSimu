package br.cesjf.dps



import org.junit.*
import grails.test.mixin.*

@TestFor(SimulationController)
@Mock(Simulation)
class SimulationControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/simulation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.simulationInstanceList.size() == 0
        assert model.simulationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.simulationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.simulationInstance != null
        assert view == '/simulation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/simulation/show/1'
        assert controller.flash.message != null
        assert Simulation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/simulation/list'

        populateValidParams(params)
        def simulation = new Simulation(params)

        assert simulation.save() != null

        params.id = simulation.id

        def model = controller.show()

        assert model.simulationInstance == simulation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/simulation/list'

        populateValidParams(params)
        def simulation = new Simulation(params)

        assert simulation.save() != null

        params.id = simulation.id

        def model = controller.edit()

        assert model.simulationInstance == simulation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/simulation/list'

        response.reset()

        populateValidParams(params)
        def simulation = new Simulation(params)

        assert simulation.save() != null

        // test invalid parameters in update
        params.id = simulation.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/simulation/edit"
        assert model.simulationInstance != null

        simulation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/simulation/show/$simulation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        simulation.clearErrors()

        populateValidParams(params)
        params.id = simulation.id
        params.version = -1
        controller.update()

        assert view == "/simulation/edit"
        assert model.simulationInstance != null
        assert model.simulationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/simulation/list'

        response.reset()

        populateValidParams(params)
        def simulation = new Simulation(params)

        assert simulation.save() != null
        assert Simulation.count() == 1

        params.id = simulation.id

        controller.delete()

        assert Simulation.count() == 0
        assert Simulation.get(simulation.id) == null
        assert response.redirectedUrl == '/simulation/list'
    }
}
