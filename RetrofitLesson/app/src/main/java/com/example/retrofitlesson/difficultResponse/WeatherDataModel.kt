package com.example.retrofitlesson.difficultResponse

data class WeatherDataModel(
    val location: LocalModel,
    val current: CurrentModel,
    val forecast: ForecastModel
)

data class LocalModel(
    val name: String,
    val localtime: String
)

data class CurrentModel(
    val last_updated: String,
    val temp_c: Float,
    val condition: Condition
)

data class Condition(
    val text: String,
    val icon: String
)

data class ForecastModel(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)

data class Day(
    val maxtemp_c: Float,
    val mintemp_c: Float,
    val condition: Condition
)

data class Hour(
    val time: String,
    val temp_c: Float,
    val condition: Condition
)