package com.binayshaw7777.clapingoassignemnt.model

enum class DayIndex {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    companion object {
        fun getIndexOfDay(dayIndex: DayIndex): Int {
            when (dayIndex) {
                SUNDAY -> {
                    return 0
                }

                MONDAY -> {
                    return 1
                }

                TUESDAY -> {
                    return 2
                }

                WEDNESDAY -> {
                    return 3
                }

                THURSDAY -> {
                    return 4
                }

                FRIDAY -> {
                    return 5
                }

                SATURDAY -> {
                    return 6
                }

                else -> {
                    return -1
                }
            }
        }

        fun getItemFromName(string: String): DayIndex {
            when (string) {
                "Sunday" -> {
                    return SUNDAY
                }

                "Monday" -> {
                    return MONDAY
                }

                "Tuesday" -> {
                    return TUESDAY
                }

                "Wednesday" -> {
                    return WEDNESDAY
                }

                "Thursday" -> {
                    return THURSDAY
                }

                "Friday" -> {
                    return FRIDAY
                }

                "Saturday" -> {
                    return SATURDAY
                }

                else -> {
                    return SUNDAY
                }
            }
        }
    }

}