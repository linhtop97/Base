package vn.com.base.libraries.utilities

/**
 * Lớp tiện ích xử lý chuỗi
 * @Created_by nblinh on 07/06/2019
 */
class StringUtility(v: String?) {
    companion object {
        private const val TAG = "StringUtility"
        fun with(value: String?): StringUtility = StringUtility(value)
        fun isNullOrEmpty(value: String?) = value == null || value.isNullOrEmpty() || value.isNullOrBlank()
    }

    private val value: String? = v

    /**
     * Phương thức xử lý khi 1 chuỗi là rỗng
     * @param consumer callback xử lý khi chuỗi rỗng
     * @Created_by nblinh on 24/06/2019
     */
    fun doIfEmpty(consumer: () -> Unit): StringUtility {
        if (isNullOrEmpty(value)) {
            consumer()
        }
        return this
    }

    /**
     * Phương thức xử lý khi 1 chuỗi có giá trị
     * @param consumer callback xử lý cới chuỗi có giá trị
     * @Created_by nblinh on 24/06/2019
     */
    fun doIfPresent(consumer: (v: String) -> Unit): StringUtility {
        if (!isNullOrEmpty(value)) {
            consumer(value!!)
        }
        return this
    }

    /**
     * Viết hoa các chữ cái đầu dòng
     * @Created_by nblinh on 24/06/2019
     */
    fun capitalize(): String? {
        if (isNullOrEmpty(value)) return ""
        val stringBuilder = StringBuilder()
        val arr: CharArray = value!!.toCharArray()
        var capitalizeNext = true
        for (v: Char in arr) {
            if (capitalizeNext && Character.isLetter(v)) {
                stringBuilder.append(Character.toUpperCase(v))
                capitalizeNext = false
                continue
            } else if (Character.isWhitespace(v)) {
                capitalizeNext = true
            }
            stringBuilder.append(v)
        }
        return stringBuilder.toString()
    }
}