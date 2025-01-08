package br.com.hellodev.common.domain.model.user

data class UserDomain(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val photo: String? = null,
    val token: String? = null
) {
    companion object {
        val userDomainDefault = UserDomain(
            id = 1,
            name = "Arley Santana",
            email = "arley.santana@gmail.com",
            photo = "https://firebasestorage.googleapis.com/v0/b/hello-job-f0f71.firebasestorage.app/o/user%2Fphoto.jpg?alt=media&token=be293f25-430d-4e58-8d90-4231f0faa39a",
            token = "token123"
        )
    }
}
