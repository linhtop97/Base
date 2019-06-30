package vn.com.base.libraries.models

import vn.com.base.libraries.models.enum.ViewTypeEnum

/**
 * Là lớp cơ sở dùng cho tất cả các màn hình sử dụng ExtRecycle View
 * @Created_by nblinh on 10/06/2019
 */
class Model(var viewType: Int = ViewTypeEnum.CONTENT.code) {
}