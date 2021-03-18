package com.example.musicsearchapp.api


import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("@attr")
    val attr: Attr,
    @SerializedName("opensearch:itemsPerPage")
    val opensearchItemsPerPage: String,
    @SerializedName("opensearch:Query")
    val opensearchQuery: OpensearchQuery,
    @SerializedName("opensearch:startIndex")
    val opensearchStartIndex: String,
    @SerializedName("opensearch:totalResults")
    val opensearchTotalResults: String,
    @SerializedName("trackmatches")
    val trackMatches: TrackMatches
)