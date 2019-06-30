package vn.com.misa.base.models

data class ErrorBody(var status: Int = 0, override var message: String? = null) : Throwable()
