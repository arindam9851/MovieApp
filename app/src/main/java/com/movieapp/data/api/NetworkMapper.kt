package com.movieapp.data.api

import com.movieapp.data.api.networkentitymodel.ResultNetworkEntity
import com.movieapp.domainmodel.Trending
import com.movieapp.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<ResultNetworkEntity,Trending>{
    override fun mapFromEntity(entity: ResultNetworkEntity): Trending {
        return Trending(
            id = entity.id,
            originalName=entity.originalName,
            voteAvegare=entity.voteAverage,
            overview=entity.overview,
            voteCount=entity.voteCount,
            imagepath=entity.posterPath
        )


    }

    override fun mapToEntity(domainModel: Trending): ResultNetworkEntity {

        return ResultNetworkEntity(
            id = domainModel.id,
            originalName=domainModel.originalName,
            voteAverage= domainModel.voteAvegare,
            overview=domainModel.overview,
            voteCount=domainModel.voteCount,
            posterPath= domainModel.imagepath
        )
    }

    fun mapFromEntityList(entities: List<ResultNetworkEntity>): List<Trending>{
        return entities.map { mapFromEntity(it) }
    }
}