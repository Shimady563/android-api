package com.shimady563.android.common.exception

data class ValidationError(
    private val violations: List<Violation>,
    private val statusCode: Int
) {
    data class Violation(
        private val message: String,
        private val fields: String
    )
}