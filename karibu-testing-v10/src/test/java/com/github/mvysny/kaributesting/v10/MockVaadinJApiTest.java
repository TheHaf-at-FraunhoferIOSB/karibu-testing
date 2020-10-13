package com.github.mvysny.kaributesting.v10;

import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinResponse;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Not really a test, doesn't run any test methods. It just fails compilation if the MockVaadin API
 * is hard to use from Java. This is an API test.
 * @author mavi
 */
public class MockVaadinJApiTest {

    public MockVaadinJApiTest() {
        UtilsKt.getMock(VaadinRequest.getCurrent()).addCookie(new Cookie("foo", "bar"));
        assertEquals("bar", UtilsKt.getMock(VaadinResponse.getCurrent()).getCookie("foo").getValue());

        MockVaadin.setup(new Routes(), () -> {
            UtilsKt.getMock(VaadinRequest.getCurrent()).addCookie(new Cookie("foo", "bar"));
            return new LocatorJApiTest.MyUI();
        });
    }
}
