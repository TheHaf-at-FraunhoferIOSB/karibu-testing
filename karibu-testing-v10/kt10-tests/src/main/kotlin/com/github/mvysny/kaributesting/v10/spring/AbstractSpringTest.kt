package com.github.mvysny.kaributesting.v10.spring

import com.github.mvysny.dynatest.jvmVersion
import com.github.mvysny.kaributesting.v10.MockVaadin
import com.github.mvysny.kaributesting.v10.ReviewsList
import com.github.mvysny.kaributesting.v10.mock.MockedUI
import com.github.mvysny.kaributesting.v10.Routes
import com.vaadin.flow.server.VaadinService
import com.vaadin.flow.server.VaadinSession
import com.vaadin.flow.spring.SpringServlet
import com.vaadin.flow.spring.SpringVaadinServletService
import com.vaadin.flow.spring.SpringVaadinSession
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assumptions.assumeFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import kotlin.test.expect

@ExtendWith(SpringExtension::class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
abstract class AbstractSpringTest(val vaadinVersion: Int) {

    private val routes: Routes = Routes()
    private val skip = vaadinVersion >= 23 && jvmVersion < 11

    @Autowired
    private lateinit var ctx: ApplicationContext

    @BeforeEach
    fun setup() {
        assumeFalse(skip, "Skipping test: Vaadin 23 requires JDK11+")

        val uiFactory: () -> MockedUI = { MockedUI() }
        val servlet: SpringServlet = MockSpringServlet(routes, ctx, uiFactory)
        MockVaadin.setup(uiFactory, servlet)
    }

    @Test
    fun testDestroyListenersCalled() {
        // check correct vaadin instances
        VaadinSession.getCurrent() as SpringVaadinSession
        VaadinService.getCurrent() as SpringVaadinServletService

        // verify that the destroy listeners are called
        var called = 0
        (VaadinSession.getCurrent() as SpringVaadinSession).addDestroyListener { called++ }
        MockVaadin.tearDown()
        expect(1) { called }
    }

    /**
     * Tests for https://github.com/mvysny/karibu-testing/issues/65
     */
    @Test
    fun testPolymerTemplateComponent() {
        assumeFalse(skip, "Skipping test: Vaadin 23 requires JDK11+")

        ReviewsList()
    }

    @AfterEach
    fun tearDown() {
        MockVaadin.tearDown()
    }
}
