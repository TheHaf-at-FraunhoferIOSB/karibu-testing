package com.github.mvysny.kaributesting.v10

import com.vaadin.flow.component.UI
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.dom.Element
import java.lang.reflect.Field
import kotlin.streams.toList

/**
 * Returns the list of currently displayed notifications.
 */
public fun getNotifications(): List<Notification> {
    // notifications are opened lazily. Make sure they are attached to the UI
    testingLifecycleHook.awaitBeforeLookup()
    val notifications = UI.getCurrent().children.toList().filterIsInstance<Notification>()
    testingLifecycleHook.awaitAfterLookup()
    return notifications
}

private val _Notification_templateElement: Field by lazy(LazyThreadSafetyMode.PUBLICATION) {
    val f: Field = Notification::class.java.getDeclaredField("templateElement")
    f.isAccessible = true
    f
}

public val Notification._templateElement: Element
    get() = _Notification_templateElement.get(this) as Element

/**
 * Returns the notification text.
 */
public fun Notification.getText(): String {
    val e: Element = _templateElement
    return e.getProperty("innerHTML") ?: ""
}

/**
 * Expects that given list of notifications is displayed. Also clears the notifications.
 */
public fun expectNotifications(vararg texts: String) {
    val notifications: List<Notification> = getNotifications()
    expectList(*texts) { notifications.map { it.getText() } }
    clearNotifications()
}

/**
 * Expects that there are no notifications displayed.
 */
public fun expectNoNotifications() {
    expectNotifications()
}

/**
 * Clears and removes all notifications from screen.
 */
public fun clearNotifications() {
    getNotifications().forEach { it.removeFromParent() }
}
