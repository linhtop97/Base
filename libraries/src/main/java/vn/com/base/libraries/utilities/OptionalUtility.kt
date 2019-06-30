package vn.com.base.libraries.utilities

/**
 * Lớp tiện ích xử lsy các đối tượng
 * @Created_by nblinh on 24/06/2019
 */
class OptionalUtility<T> private constructor(v: T?) {
    companion object {
        fun <T> with(value: T?): OptionalUtility<T> = OptionalUtility(value)

        fun <T> isNullOrEmpty(value: T?): Boolean = value == null
    }

    private val value: T? = v

    /**
     * Phương thức xử lý với 1 đối tượng
     * @param consumer callback xử lý khi đối tượng không có giá trị
     * @Created_by nblinh on 24/06/2019
     */
    fun doIfEmpty(consumer: () -> Unit): OptionalUtility<T> {
        if (isNullOrEmpty(value)) {
            consumer()
        }

        return this
    }

    /**
     * Phương thức xử lý khi đối tượng có giá trị
     * @param consumer callback xử lý khi đối tượng có giá trị
     * @Created_by nblinh on 24/06/2019
     */
    fun doIfPresent(consumer: (t: T) -> Unit): OptionalUtility<T> {
        if (!isNullOrEmpty(value)) {
            consumer(value!!)
        }
        return this
    }

    /**
     * Phương thức lấy về thể hiện của lớp
     * @Created_by nblinh on 24/06/2019
     */
    fun get(): T? = value

    /**
     * Phương thức lấy về thể hiện giá trị của lớp, đối với thể hiện là @null thì sẽ lấy giá trị mặc định
     * @param defaultVal giá tị mặc định
     * @Created_by nblinh on 24/06/2019
     */
    fun get(defaultVal: T?): T? = value ?: defaultVal
}