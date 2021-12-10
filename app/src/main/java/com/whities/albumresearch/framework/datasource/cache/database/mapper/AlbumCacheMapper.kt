package com.whities.albumresearch.framework.datasource.cache.database.mapper

import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.util.EntityMapper
import com.whities.albumresearch.framework.datasource.cache.database.model.AlbumCacheEntity
import javax.inject.Inject

class AlbumCacheMapper
@Inject
constructor() : EntityMapper<AlbumCacheEntity, Album> {

    override fun mapFromEntity(entity: AlbumCacheEntity): Album {
        return Album(
            artistId = entity.artistId,
            artistName = entity.artistName,
            collectionId = entity.collectionId,
            collectionName = entity.collectionName,
            artworkUrl100 = entity.artworkUrl100,
            collectionCensoredName = entity.collectionCensoredName,
            collectionExplicitness = entity.collectionExplicitness,
            collectionType = entity.collectionType,
            copyright = entity.copyright,
            primaryGenreName = entity.primaryGenreName,
            releaseDate = entity.releaseDate,
            trackCount = entity.trackCount,
            wrapperType = entity.wrapperType
        )
    }

    override fun mapToEntity(domainModel: Album): AlbumCacheEntity {
        return AlbumCacheEntity(
            artistId = domainModel.artistId,
            artistName = domainModel.artistName,
            collectionId = domainModel.collectionId,
            collectionName = domainModel.collectionName,
            artworkUrl100 = domainModel.artworkUrl100,
            collectionCensoredName = domainModel.collectionCensoredName,
            collectionExplicitness = domainModel.collectionExplicitness,
            collectionType = domainModel.collectionType,
            copyright = domainModel.copyright,
            primaryGenreName = domainModel.primaryGenreName,
            releaseDate = domainModel.releaseDate,
            trackCount = domainModel.trackCount,
            wrapperType = domainModel.wrapperType
        )
    }

    fun mapFromEntityList(entities: List<AlbumCacheEntity>): List<Album> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(domainModels: List<Album>): List<AlbumCacheEntity> {
        return domainModels.map { mapToEntity(it) }
    }
}
