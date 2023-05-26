package com.orra.pet.utils

inline fun <T> List<T>.updateAt(position: Int, action: (T) -> T): List<T> =
    mapIndexed { index, item ->
        if (index == position) {
            action.invoke(item)
        } else {
            item
        }
    }


inline fun <T> makeList(fill: MutableList<T>.() -> Unit): List<T> {
    return ArrayList<T>().apply { fill(this) }
}

inline fun <K, V> makeMap(fill: MutableMap<K, V>.() -> Unit): Map<K, V> {
    return HashMap<K, V>().apply { fill(this) }
}

inline fun <K> makeSet(fill: MutableSet<K>.() -> Unit): Set<K> {
    return mutableSetOf<K>().apply { fill(this) }
}

fun <T> List<T>.safeSubList(toIndex: Int): List<T> = safeSubList(0, toIndex)

fun <T> List<T>.safeSubList(fromIndex: Int, toIndex: Int): List<T> {
    return when {
        toIndex in 0..size && fromIndex in 0..lastIndex -> {
            subList(fromIndex, toIndex).toList()
        }

        else -> this
    }
}

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}
