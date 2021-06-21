@file:Suppress("FunctionName")

package com.github.mvysny.kaributesting.v10

import com.vaadin.flow.component.UI
import com.vaadin.flow.router.RouterLink

/**
 * Navigates to where this router link points to.
 * @throws IllegalStateException if the link was not visible, not enabled. See [checkEditableByUser] for
 * more details.
 */
public fun RouterLink._click() {
    checkEditableByUser()
    click()
}

/**
 * Navigates to where this router link points to. The difference to [_click] is that this one doesn't check whether
 * the link is actually visible and enabled.
 */
public fun RouterLink.click() {
    UI.getCurrent().navigate(href)
}
