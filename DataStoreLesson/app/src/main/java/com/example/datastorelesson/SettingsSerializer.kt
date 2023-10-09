package com.example.datastorelesson

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<SettingsDataProto> {
    override val defaultValue: SettingsDataProto
        get() = SettingsDataProto()

    override suspend fun readFrom(input: InputStream): SettingsDataProto {
        return try {
            Json.decodeFromString(
                deserializer = SettingsDataProto.serializer(),
                string = input.readBytes().toString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            SettingsDataProto()
        }
    }

    override suspend fun writeTo(t: SettingsDataProto, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = SettingsDataProto.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}