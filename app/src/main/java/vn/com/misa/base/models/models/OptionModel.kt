package vn.com.misa.base.models.models

import vn.com.misa.base.models.enums.OptionEnum

data class OptionModel(val content: String?, val viewType: OptionEnum = OptionEnum.OPTION_ITEM)