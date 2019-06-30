package vn.com.misa.base.utilities

class Constant {
    companion object {
        // format date
        const val FORMAT_DATE_SERVER_RETURN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val FORMAT_YEAR_MONTH_DAY = "yyyy/MM/dd"
        const val FORMAT_DAY_MONTH_YEAR = "dd/MM/yyyy"

        // paging
        const val NUMBER_PER_PAGE = 20

        // language
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_VIETNAMESE = "vi"
        const val DEFAULT_LANGUAGE = "DEFAULT_LANGUAGE"

        // time next screen
        const val TIME_DELAY_NEXT_SCREEN = 2L // seconds
        const val TIME_DELAY_INIT_SCREEN = 20L // miliseconds

        // toast
        const val TOAST_TIME = 2500L

        // user info
        const val SIGN_IN_RESPONSE = "sign_in_response"

        // sort
        const val SORT_NEWEST = "DESC"
        const val SORT_OLDEST = "ASC"
    }
}
