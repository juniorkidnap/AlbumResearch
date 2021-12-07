package com.whities.albumresearch.framework.datasource.network.mappers

import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.business.domain.util.EntityMapper
import com.whities.albumresearch.framework.datasource.network.model.TrackNetworkEntity
import javax.inject.Inject

class AlbumNetworkMapper
@Inject
constructor() : EntityMapper<TrackNetworkEntity, Track> {

    override fun mapFromEntity(entity: TrackNetworkEntity): Track {
        return Track(
            artistId = entity.artistId,
            artistName = entity.artistName,
            collectionId = entity.collectionId,
            collectionName = entity.collectionName,
            collectionType = entity.collectionType,
            artworkUrl100 = entity.artworkUrl100,
            collectionCensoredName = entity.collectionCensoredName,
            collectionExplicitness = entity.collectionExplicitness,
            copyright = entity.copyright,
            country = entity.country,
            kind = entity.kind,
            primaryGenreName = entity.primaryGenreName,
            releaseDate = entity.releaseDate.substringBefore("-"),
            trackCensoredName = entity.trackCensoredName,
            trackCount = entity.trackCount,
            trackExplicitness = entity.trackExplicitness,
            trackId = entity.trackId,
            trackName = entity.trackName,
            trackNumber = entity.trackNumber,
            trackTimeMillis = entity.trackTimeMillis,
            wrapperType = entity.wrapperType
        )
    }

    override fun mapToEntity(domainModel: Track): TrackNetworkEntity {
        return TrackNetworkEntity(
            artistId = domainModel.artistId,
            artistName = domainModel.artistName,
            collectionId = domainModel.collectionId,
            collectionName = domainModel.collectionName,
            collectionType = domainModel.collectionType,
            artworkUrl100 = domainModel.artworkUrl100,
            collectionCensoredName = domainModel.collectionCensoredName,
            collectionExplicitness = domainModel.collectionExplicitness,
            copyright = domainModel.copyright,
            country = domainModel.country,
            kind = domainModel.kind,
            primaryGenreName = domainModel.primaryGenreName,
            releaseDate = domainModel.releaseDate,
            trackCensoredName = domainModel.trackCensoredName,
            trackCount = domainModel.trackCount,
            trackExplicitness = domainModel.trackExplicitness,
            trackId = domainModel.trackId,
            trackName = domainModel.trackName,
            trackNumber = domainModel.trackNumber,
            trackTimeMillis = domainModel.trackTimeMillis,
            wrapperType = domainModel.wrapperType
        )
    }

    fun mapFromEntityList(entities: List<TrackNetworkEntity>): List<Track> {
        return entities.map { mapFromEntity(it) }
    }
}
