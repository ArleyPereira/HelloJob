package br.com.hellodev.common.domain.model.home

import br.com.hellodev.common.domain.model.banner.BannerDomain
import br.com.hellodev.common.domain.model.job.section.JobSectionDomain
import br.com.hellodev.common.domain.model.user.UserDomain

data class HomeDomain(
    val profile: UserDomain? = null,
    val banners: List<BannerDomain>? = null,
    val recommendation: JobSectionDomain? = null,
    val recent: JobSectionDomain? = null
) {
    companion object {
        val homeDomainDefault = HomeDomain(
            profile = UserDomain.userDomainDefault,
            banners = BannerDomain.items,
            recommendation = JobSectionDomain.items[0],
            recent = JobSectionDomain.items[1]
        )
    }
}
