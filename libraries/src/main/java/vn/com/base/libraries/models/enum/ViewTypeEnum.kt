package vn.com.base.libraries.models.enum

/**
 * Lớp Enum định kiểu cho model trong recyclerView mặc định
 * @Created_by nblinh on 10/06/2019
 */
enum class ViewTypeEnum(var code: Int) {
    OTHER(-1), LOADING(0), TITLE(1), CONTENT(2)
}