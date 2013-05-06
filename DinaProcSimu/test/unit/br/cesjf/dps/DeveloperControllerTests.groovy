package br.cesjf.dps



import org.junit.*
import grails.test.mixin.*

@TestFor(DeveloperController)
@Mock(Developer)
class DeveloperControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/developer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.developerInstanceList.size() == 0
        assert model.developerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.developerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.developerInstance != null
        assert view == '/developer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/developer/show/1'
        assert controller.flash.message != null
        assert Developer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/developer/list'

        populateValidParams(params)
        def developer = new Developer(params)

        assert developer.save() != null

        params.id = developer.id

        def model = controller.show()

        assert model.developerInstance == developer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/developer/list'

        populateValidParams(params)
        def developer = new Developer(params)

        assert developer.save() != null

        params.id = developer.id

        def model = controller.edit()

        assert model.developerInstance == developer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/developer/list'

        response.reset()

        populateValidParams(params)
        def developer = new Developer(params)

        assert developer.save() != null

        // test invalid parameters in update
        params.id = developer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/developer/edit"
        assert model.developerInstance != null

        developer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/developer/show/$developer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        developer.clearErrors()

        populateValidParams(params)
        params.id = developer.id
        params.version = -1
        controller.update()

        assert view == "/developer/edit"
        assert model.developerInstance != null
        assert model.developerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/developer/list'

        response.reset()

        populateValidParams(params)
        def developer = new Developer(params)

        assert developer.save() != null
        assert Developer.count() == 1

        params.id = developer.id

        controller.delete()

        assert Developer.count() == 0
        assert Developer.get(developer.id) == null
        assert response.redirectedUrl == '/developer/list'
    }
}
