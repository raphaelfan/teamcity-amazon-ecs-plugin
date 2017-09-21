package jetbrains.buildServer.clouds.ecs

import jetbrains.buildServer.clouds.CloudErrorInfo
import jetbrains.buildServer.clouds.CloudImage
import jetbrains.buildServer.clouds.InstanceStatus
import jetbrains.buildServer.clouds.ecs.apiConnector.EcsApiConnector
import jetbrains.buildServer.clouds.ecs.apiConnector.EcsTask
import jetbrains.buildServer.serverSide.AgentDescription
import java.util.*

class EcsCloudInstanceImpl(val cloudImage: EcsCloudImage, val ecsTask: EcsTask, val apiConnector: EcsApiConnector) : EcsCloudInstance {
    private var myCurrentError: CloudErrorInfo? = null

    override fun getStatus(): InstanceStatus {
        val task = apiConnector.describeTask(ecsTask.arn)
        if(task == null) return InstanceStatus.UNKNOWN
        val lastStatus = task.lastStatus
        val desiredStatus = task.desiredStatus
        when (desiredStatus) {
            "RUNNING" -> {
                when(lastStatus){
                    "PENDING" -> return InstanceStatus.STARTING
                    "RUNNING" -> return InstanceStatus.RUNNING
                    else -> return InstanceStatus.RUNNING
                }
            }
            "STOPPED" -> {
                when(lastStatus){
                    "RUNNING" -> return InstanceStatus.STOPPING
                    "PENDING" -> return InstanceStatus.STOPPED
                    "STOPPED" -> return InstanceStatus.STOPPED
                    else -> return InstanceStatus.STOPPED
                }
            }
            else -> return InstanceStatus.UNKNOWN
        }
    }

    override fun getInstanceId(): String {
        return ecsTask.arn
    }

    override fun getName(): String {
        return ecsTask.arn
    }

    override fun getStartedTime(): Date {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNetworkIdentity(): String? {
        //TODO: provide identity
        return null
    }

    override fun getImage(): CloudImage {
        return cloudImage
    }

    override fun getImageId(): String {
        return cloudImage.id
    }

    override fun getErrorInfo(): CloudErrorInfo? {
        return myCurrentError
    }

    override fun containsAgent(agent: AgentDescription): Boolean {
        return name == agent.configurationParameters.get(INSTANCE_NAME_AGENT_PROP)
    }

    override fun terminate() {
        //TODO: provide cluster
        apiConnector.stopTask(ecsTask.arn, null, "Terminated by TeamCity server")
        myCurrentError = null
        cloudImage.deleteInstance(this)
    }
}
