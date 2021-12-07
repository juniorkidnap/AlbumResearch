package com.whities.albumresearch.framework.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.databinding.RowTrackBinding

class AlbumListAdapter : ListAdapter<Track, AlbumListAdapter.ViewHolder>(DiffUtilCallback) {

    inner class ViewHolder(private val itemBinding: RowTrackBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private var currentTrack: Track? = null

        fun bind(track: Track) = with(itemBinding) {

            currentTrack = track.apply {
                trackTitlePreview.text = trackCensoredName
                trackArtistPreview.text = artistName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            RowTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }
}

object DiffUtilCallback : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem
    }

}
