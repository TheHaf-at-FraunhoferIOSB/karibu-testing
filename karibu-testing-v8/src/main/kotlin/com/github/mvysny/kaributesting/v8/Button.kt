@file:Suppress("FunctionName")

package com.github.mvysny.kaributesting.v8

import com.vaadin.ui.Button

/**
 * Clicks the button, but only if it is actually possible to do so by the user.
 * If the button is read-only or disabled, an exception is thrown.
 *
 * See [_checkClickable] for more details.
 * @throws IllegalStateException if the button was not visible or not enabled.
 */
fun Button._click() {
    _checkClickable()
    click()
}

/**
 * Alias for [checkEditableByUser].
 */
fun Button._checkClickable() {
    checkEditableByUser()
}
