package com.movieapp.room

import com.movieapp.data.api.networkentitymodel.ResultNetworkEntity
import com.movieapp.domainmodel.Trending
import com.movieapp.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<TrendingCacheEntity,Trending>
{
    override fun mapFromEntity(entity: TrendingCacheEntity): Trending {
        return Trending(
            id = entity.id,
            originalName=entity.originalName,
            voteAvegare=entity.voteAvegare,
            overview=entity.overview,
            voteCount=entity.voteCount,
            imagepath=entity.imagepath
        )
    }

    override fun mapToEntity(domainModel: Trending): TrendingCacheEntity {

        return TrendingCacheEntity(
            id = domainModel.id,
            originalName=domainModel.originalName,
            voteAvegare=domainModel.voteAvegare,
            overview=domainModel.overview,
            voteCount=domainModel.voteCount,
            imagepath=domainModel.imagepath
        )
    }

    fun mapFromEntityList(entities: List<TrendingCacheEntity>): List<Trending>{
        return entities.map { mapFromEntity(it) }
    }
}