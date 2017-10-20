package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override operator fun compareTo(other: MyDate): Int {
        return if (year == other.year && month == other.month && dayOfMonth == other.dayOfMonth) 0
        else if (year > other.year || (year == other.year && month > other.month) ||
                (year == other.year && month == other.month && dayOfMonth > other.dayOfMonth)) 1
        else -1
    }

    operator fun plus(timeInterval: TimeInterval): MyDate {
        return this.copy().addTimeIntervals(timeInterval, 1)
    }

    operator fun plus(timeInterval: MultipleTimeInterval): MyDate {
        return this.copy().addTimeIntervals(timeInterval.interval, timeInterval.count)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(count: Int): MultipleTimeInterval {
        return MultipleTimeInterval(count, this)
    }
}

data class MultipleTimeInterval(val count: Int, val interval: TimeInterval)

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(date: MyDate): Boolean {
        return (start <= date) && (date <= endInclusive)
    }

    operator fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var currentDay: MyDate = start

            override fun next(): MyDate {
                val temp = currentDay
                currentDay = currentDay.nextDay()
                return temp
            }

            override fun hasNext(): Boolean {
                return currentDay <= endInclusive
            }
        }
    }
}
