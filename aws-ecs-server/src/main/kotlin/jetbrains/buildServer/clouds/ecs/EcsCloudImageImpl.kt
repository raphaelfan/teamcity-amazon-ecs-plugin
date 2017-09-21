package jetbrains.buildServer.clouds.ecs

import jetbrains.buildServer.clouds.CloudErrorInfo
import jetbrains.buildServer.clouds.CloudInstance
import jetbrains.buildServer.clouds.ecs.apiConnector.EcsApiConnector

class EcsCloudImageImpl(private val imageData: EcsCloudImageData, private val apiConnector: EcsApiConnector) : EcsCloudImage {
    override val instanceLimit: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val instanceCount: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val taskDefinition: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val cluster: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val taskGroup: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun getAgentPoolId(): Int? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getName(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getId(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInstances(): MutableCollection<out CloudInstance> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getErrorInfo(): CloudErrorInfo? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findInstanceById(id: String): CloudInstance? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun populateInstances() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}