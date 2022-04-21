package com.amrabdelhamiddiab.retrofitme3.data.model

data class Medicine(
    var id_medicine: String = "",
    val code_medicine: Long = 0L,
    val name_en_Medicine:String = "",
    val name_ar_Medicine: String? = "",
    val name_clinical_medicine: String = "",
    val category_en_medicine: String = "",
    val category_ar_medicine: String? = "",
    val img_url_medicine: String? = "",
    val description_ar_url_medicine: String = "",
    val company_name_medicine: String = "",
    val price_medicine: String = "",
    val availability_medicine: Boolean = true,
    val offer_on_medicine: Boolean = false,
    val alternatives_url_medicine: String? = "")