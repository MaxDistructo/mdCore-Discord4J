package maxdistructo.discord.core.impl

//This code borrows the Webhook class from Discord4J
//The above mentioned code was released under a GNU Lesser General Public License at https://github.com/Discord4J/Discord4J
//The only modification of this code was a convert to Kotlin and an unprotecting of elements of the object.

import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.api.IShard
import sx.blah.discord.api.internal.DiscordClientImpl
import sx.blah.discord.api.internal.DiscordEndpoints
import sx.blah.discord.api.internal.DiscordUtils
import sx.blah.discord.api.internal.json.objects.WebhookObject
import sx.blah.discord.api.internal.json.requests.WebhookEditRequest
import sx.blah.discord.handle.impl.events.guild.channel.webhook.WebhookUpdateEvent
import sx.blah.discord.handle.obj.*
import sx.blah.discord.util.Image
import sx.blah.discord.util.PermissionUtils

import java.util.Objects

/**
 * The default implementation of [IWebhook].
 */
class Webhook(
        /**
         * The client the object belongs to.
         */
        internal val client: IDiscordClient,
        /**
         * The webhook's default name.
         */
        @field:Volatile internal var name: String,
        /**
         * The unique snowflake ID of the webhook.
         */
        internal val id: Long,
        /**
         * The parent channel lof the webhook.
         */
        internal val channel: IChannel,
        /**
         * The user who created the webhook.
         */
        internal val author: IUser,
        /**
         * The webhook's default avatar.
         */
        @field:Volatile internal var avatar: String,
        /**
         * The webhook's secure token.
         */
        internal val token: String) : IWebhook {

    override fun getLongID(): Long {
        return id
    }

    override fun getClient(): IDiscordClient {
        return client
    }

    override fun getShard(): IShard {
        return channel.shard
    }

    override fun copy(): IWebhook {
        return Webhook(client, name, id, channel, author, avatar, token)
    }

    override fun getGuild(): IGuild {
        return channel.guild
    }

    override fun getChannel(): IChannel {
        return channel
    }

    override fun getAuthor(): IUser {
        return author
    }

    override fun getDefaultName(): String {
        return name
    }

    override fun getDefaultAvatar(): String {
        return avatar
    }

    override fun getToken(): String {
        return token
    }

    /**
     * Sends a request to edit the webhook.
     *
     * @param name The default name of the webhook.
     * @param avatar The base64-encoded default avatar of the webhook.
     */
    private fun edit(name: String, avatar: String?) {
        PermissionUtils.requirePermissions(channel, client.ourUser, Permissions.MANAGE_WEBHOOKS)

        val response = (client as DiscordClientImpl).REQUESTS.PATCH.makeRequest(
                DiscordEndpoints.WEBHOOKS + id,
                WebhookEditRequest(name, avatar),
                WebhookObject::class.java)

        val oldWebhook = copy()
        val newWebhook = DiscordUtils.getWebhookFromJSON(channel, response)

        client.getDispatcher().dispatch(WebhookUpdateEvent(oldWebhook, newWebhook))
    }

    override fun changeDefaultName(name: String) {
        edit(name, null)
    }

    override fun changeDefaultAvatar(avatar: String) {
        edit(this.name, avatar)
    }

    override fun changeDefaultAvatar(avatar: Image) {
        edit(this.name, avatar.data)
    }

    /**
     * Sets the CACHED name of the webhook.
     *
     * @param name The name.
     */
    fun setName(name: String) {
        this.name = name
    }

    /**
     * Sets the CACHED avatar of the webhook.
     *
     * @param avatar The avatar.
     */
    fun setAvatar(avatar: String) {
        this.avatar = avatar
    }

    override fun delete() {
        PermissionUtils.requirePermissions(channel, client.ourUser, Permissions.MANAGE_WEBHOOKS)

        (client as DiscordClientImpl).REQUESTS.DELETE.makeRequest(DiscordEndpoints.WEBHOOKS + id)
    }

    override fun isDeleted(): Boolean {
        return getChannel().getWebhookByID(id) !== this
    }

    override fun toString(): String {
        return name
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun equals(other: Any?): Boolean {
        return DiscordUtils.equals(this, other)
    }
}
