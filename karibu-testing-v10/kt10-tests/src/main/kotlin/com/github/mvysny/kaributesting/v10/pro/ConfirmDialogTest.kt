package com.github.mvysny.kaributesting.v10.pro

import com.github.mvysny.dynatest.DynaNodeGroup
import com.github.mvysny.dynatest.DynaTestDsl
import com.github.mvysny.kaributesting.v10.MockVaadin
import com.github.mvysny.kaributesting.v10._expectNone
import com.github.mvysny.kaributesting.v10._expectOne
import com.github.mvysny.kaributesting.v10._get
import com.vaadin.flow.component.confirmdialog.ConfirmDialog
import kotlin.test.expect
import kotlin.test.fail

@DynaTestDsl
internal fun DynaNodeGroup.confirmDialogTestbatch() {

    beforeEach { MockVaadin.setup() }
    afterEach { MockVaadin.tearDown() }

    test("smoke test") {
        ConfirmDialog("Foo", "Bar", "Yes") {}.open()
        _expectOne<ConfirmDialog>()
    }

    test("confirm") {
        var confirmCalled = false
        ConfirmDialog("Foo", "Bar", "Yes") { confirmCalled = true }.open()
        _get<ConfirmDialog>()._fireConfirm()
        expect(true) { confirmCalled }
        _expectNone<ConfirmDialog>()  // make sure the ConfirmDialog is closed: https://github.com/mvysny/karibu-testing/issues/34
    }

    test("cancel") {
        var cancelCalled = false
        val dialog = ConfirmDialog("Foo", "Bar", "Yes") { fail("unexpected") }
        dialog.addCancelListener { cancelCalled = true }
        dialog.open()
        _get<ConfirmDialog>()._fireCancel()
        expect(true) { cancelCalled }
        _expectNone<ConfirmDialog>()  // make sure the ConfirmDialog is closed: https://github.com/mvysny/karibu-testing/issues/34
    }

    test("reject") {
        var rejectCalled = false
        val dialog = ConfirmDialog("Foo", "Bar", "Yes") { fail("unexpected") }
        dialog.addCancelListener { fail("unexpected") }
        dialog.addRejectListener { rejectCalled = true }
        dialog.open()
        _get<ConfirmDialog>()._fireReject()
        expect(true) { rejectCalled }
        _expectNone<ConfirmDialog>()  // make sure the ConfirmDialog is closed: https://github.com/mvysny/karibu-testing/issues/34
    }
}
