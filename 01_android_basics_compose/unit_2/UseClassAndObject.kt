import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RangeRegulator(
  initialValue: Int,
  private val minValue: Int,
  private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {
    var fieldData: Int = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

// open class SmartDevice(val name: String, val category: String) {
// Specify a modifier for the primary constructor, the keyword `constructor` must be used
// `classModifier class Name constructorModifier constructor(parameters) { ... }`
open class SmartDevice protected constructor(val name: String, val category: String) {
    var deviceStatus: String = "online"
        // `protected set(value) { field = value }` can shorten to the following
        protected set

    open val deviceType = "unknown"

    constructor(name: String, category: String, statusCode: Int): this(name, category) {
        deviceStatus = when(statusCode) {
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
        }
    }

    fun printDeviceInfo() {
      println("Device name: $name, category: $category, type: $deviceType")
    }

    open fun turnOn() {
      deviceStatus = "on"
    }
    open fun turnOff() {
      deviceStatus = "off"
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {
        override val deviceType = "Smart TV"

        private var speakerVolume by RangeRegulator(2, 0, 100)
        private var channelNumber by RangeRegulator(1, 0, 200)

        override fun turnOn() {
          super.turnOn()
          println("$name turned on. Speaker volume set to $speakerVolume and channel number set to $channelNumber.")
        }
        override fun turnOff() {
          super.turnOff()
          println("$name turned off.")
        }
        fun increaseSpeakerVolume() {
            if (deviceStatus == "on") {
                speakerVolume++
                println("Speaker volume increased to $speakerVolume.")
            }
        }
        fun decreaseSpeakerVolume() {
            if (deviceStatus == "on") {
                speakerVolume--
                println("Speaker volume decreased to $speakerVolume.")
            }
        }
        fun nextChannel() {
            if (deviceStatus == "on") {
                channelNumber++
                println("Channel number increased to $channelNumber.")
            }
        }
        fun previousChannel() {
            if (deviceStatus == "on") {
                channelNumber--
                println("Channel number decreased to $channelNumber.")
            }
        }
    }

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {
        override val deviceType = "Smart Light"
        private var brightnessLevel by RangeRegulator(2, 0, 100)

        override fun turnOn() {
          super.turnOn()
          brightnessLevel = 2
          println("$name turned on. The brightness level is $brightnessLevel.")
        }
        override fun turnOff() {
          super.turnOff()
          brightnessLevel = 0
          println("Smart Light turned off.")
        }
        fun increaseBrightness() {
            if (deviceStatus == "on") {
                brightnessLevel++
                println("Brightness increased to $brightnessLevel.")
            }
        }
        fun decreaseBrightness() {
            if (deviceStatus == "on") {
                brightnessLevel--
                println("Brightness decreased to $brightnessLevel.")
            }
        }
    }

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {
    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }
    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }
    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }
    fun decreaseTvVolume() {
        smartTvDevice.decreaseSpeakerVolume()
    }
    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }
    fun changeTvChannelToPrevious() {
        smartTvDevice.previousChannel()
    }
    fun printSmartTvInfo() {
        smartTvDevice.printDeviceInfo()
    }
    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }
    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }
    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }
    fun decreaseLightBrightness() {
        smartLightDevice.decreaseBrightness()
    }
    fun printSmartLightInfo() {
        smartLightDevice.printDeviceInfo()
    }
    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

fun main() {
    val smartHome = SmartHome(
      SmartTvDevice("Android TV", "Entertainment"),
      SmartLightDevice("Google Light", "Utility")
    )
    smartHome.increaseTvVolume()
    println()

    smartHome.turnOnTv()
    smartHome.turnOnLight()
    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}")
    println()

    smartHome.increaseTvVolume()
    smartHome.changeTvChannelToNext()
    smartHome.increaseLightBrightness()
    println()

    smartHome.decreaseTvVolume()
    smartHome.changeTvChannelToPrevious()
    smartHome.decreaseLightBrightness()
    smartHome.decreaseLightBrightness()
    smartHome.decreaseLightBrightness()
    smartHome.decreaseLightBrightness()
    println()

    smartHome.turnOffAllDevices()
    println("Total number of devices currently turned on: ${smartHome.deviceTurnOnCount}")
}
