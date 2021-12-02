package com.whities.albumresearch.framework.datasource.network.mappers

import android.util.Log
import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.util.EntityMapper
import com.whities.albumresearch.framework.datasource.network.model.AnswerNetworkEntity
import javax.inject.Inject

class SearchResultNetworkMapper
@Inject
constructor() : EntityMapper<AnswerNetworkEntity, Album> {

    override fun mapFromEntity(entity: AnswerNetworkEntity): Album {

        return Album(
            collectionId = entity.collectionId,
            artistId = entity.artistId,
            artistName = entity.artistName,
            collectionName = entity.collectionName,
            collectionType = entity.collectionType,
            artworkUrl100 = entity.artworkUrl100,
            collectionCensoredName = entity.collectionCensoredName,
            collectionExplicitness = entity.collectionExplicitness,
            copyright = entity.copyright,
            primaryGenreName = entity.primaryGenreName,
            releaseDate = entity.releaseDate.substringBefore("-"),
            trackCount = entity.trackCount,
            wrapperType = entity.wrapperType,
        )
    }

    override fun mapToEntity(domainModel: Album): AnswerNetworkEntity {
        return AnswerNetworkEntity(
            collectionId = domainModel.collectionId,
            artistId = domainModel.artistId,
            artistName = domainModel.artistName,
            collectionName = domainModel.collectionName,
            collectionType = domainModel.collectionType,
            artworkUrl100 = domainModel.artworkUrl100,
            collectionCensoredName = domainModel.collectionCensoredName,
            collectionExplicitness = domainModel.collectionExplicitness,
            copyright = domainModel.copyright,
            primaryGenreName = domainModel.primaryGenreName,
            releaseDate = domainModel.releaseDate,
            trackCount = domainModel.trackCount,
            wrapperType = domainModel.wrapperType,
        )
    }

    fun mapFromEntityList(entities: List<AnswerNetworkEntity>): List<Album> {
        return entities.map { mapFromEntity(it) }
    }
}