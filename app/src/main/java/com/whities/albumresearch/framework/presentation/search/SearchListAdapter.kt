package com.whities.albumresearch.framework.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.whities.albumresearch.R
import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.databinding.RowAlbumPreviewBinding


class SearchListAdapter(
    private val context: Context,
    private val onClick: (Album) -> Unit
) : ListAdapter<Album, SearchListAdapter.ViewHolder>(DiffUtilCallback) {

    inner class ViewHolder(
        private val itemBinding: RowAlbumPreviewBinding,
        val onClick: (Album) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private var currentAlbum: Album? = null

        fun bind(album: Album) = with(itemBinding) {
            currentAlbum = album.apply {
                albumTitlePreview.text = this.collectionName
                albumArtistPreview.text = this.artistName
                loadImage(albumCoverPreview, this.artworkUrl100)
            }

            itemView.setOnClickListener {
                currentAlbum?.let { album ->
                    onClick(album)
                }
            }
        }

        private fun loadImage(imageView: ImageView, url: String) {

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_album_24)
                .error(R.drawable.ic_baseline_album_24)
            Glide.with(context).load(url).apply(options).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            RowAlbumPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }
}

object DiffUtilCallback : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.collectionId == newItem.collectionId
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }

}
