package vn.com.misa.base.models.events

import vn.com.base.libraries.rxbus.IEvent
import vn.com.misa.base.utilities.ErrorCodes

data class NetworkEvent(var errorCode: Int = 0, var message: String = "", var showMessage: Boolean = false) : IEvent {
    override fun toString(): String {
        return "ErrorCode: $errorCode - ${ErrorCodes.getErrorTextFromCode(errorCode)}, Message: $message, ShowMessage: $showMessage"
    }
}