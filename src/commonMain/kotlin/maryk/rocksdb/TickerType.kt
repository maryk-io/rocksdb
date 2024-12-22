package maryk.rocksdb

/**
 * The logical mapping of tickers defined in rocksdb::Tickers.
 *
 * Java byte value mappings don't align 1:1 to the c++ values. c++ rocksdb::Tickers enumeration type
 * is uint32_t and java org.rocksdb.TickerType is byte, this causes mapping issues when
 * rocksdb::Tickers value is greater then 127 (0x7F) for jbyte jni interface as range greater is not
 * available. Without breaking interface in minor versions, value mappings for
 * org.rocksdb.TickerType leverage full byte range [-128 (-0x80), (0x7F)]. Newer tickers added
 * should descend into negative values until TICKER_ENUM_MAX reaches -128 (-0x80).
 */
expect enum class TickerType {
    /**
     * total block cache misses
     *
     * REQUIRES: BLOCK_CACHE_MISS == BLOCK_CACHE_INDEX_MISS +
     *     BLOCK_CACHE_FILTER_MISS +
     *     BLOCK_CACHE_DATA_MISS;
     */
    BLOCK_CACHE_MISS,

    /**
     * total block cache hit
     *
     * REQUIRES: BLOCK_CACHE_HIT == BLOCK_CACHE_INDEX_HIT +
     *     BLOCK_CACHE_FILTER_HIT +
     *     BLOCK_CACHE_DATA_HIT;
     */
    BLOCK_CACHE_HIT,
    // # of blocks added to block cache.
    BLOCK_CACHE_ADD,
    // # of failures when adding blocks to block cache.
    BLOCK_CACHE_ADD_FAILURES,
    // # of times cache miss when accessing index block from block cache.
    BLOCK_CACHE_INDEX_MISS,
    // # of times cache hit when accessing index block from block cache.
    BLOCK_CACHE_INDEX_HIT,
    // # of index blocks added to block cache.
    BLOCK_CACHE_INDEX_ADD,
    // # of bytes of index blocks inserted into cache
    BLOCK_CACHE_INDEX_BYTES_INSERT,
    // # of times cache miss when accessing filter block from block cache.
    BLOCK_CACHE_FILTER_MISS,
    // # of times cache hit when accessing filter block from block cache.
    BLOCK_CACHE_FILTER_HIT,
    // # of filter blocks added to block cache.
    BLOCK_CACHE_FILTER_ADD,
    // # of bytes of bloom filter blocks inserted into cache
    BLOCK_CACHE_FILTER_BYTES_INSERT,
    // # of times cache miss when accessing data block from block cache.
    BLOCK_CACHE_DATA_MISS,
    // # of times cache hit when accessing data block from block cache.
    BLOCK_CACHE_DATA_HIT,
    // # of data blocks added to block cache.
    BLOCK_CACHE_DATA_ADD,
    // # of bytes of data blocks inserted into cache
    BLOCK_CACHE_DATA_BYTES_INSERT,
    // # of bytes read from cache.
    BLOCK_CACHE_BYTES_READ,
    // # of bytes written into cache.
    BLOCK_CACHE_BYTES_WRITE,

    BLOCK_CACHE_COMPRESSION_DICT_MISS,
    BLOCK_CACHE_COMPRESSION_DICT_HIT,
    BLOCK_CACHE_COMPRESSION_DICT_ADD,
    BLOCK_CACHE_COMPRESSION_DICT_BYTES_INSERT,

    // # of blocks redundantly inserted into block cache.
    // REQUIRES: BLOCK_CACHE_ADD_REDUNDANT <= BLOCK_CACHE_ADD
    BLOCK_CACHE_ADD_REDUNDANT,
    // # of index blocks redundantly inserted into block cache.
    // REQUIRES: BLOCK_CACHE_INDEX_ADD_REDUNDANT <= BLOCK_CACHE_INDEX_ADD
    BLOCK_CACHE_INDEX_ADD_REDUNDANT,
    // # of filter blocks redundantly inserted into block cache.
    // REQUIRES: BLOCK_CACHE_FILTER_ADD_REDUNDANT <= BLOCK_CACHE_FILTER_ADD
    BLOCK_CACHE_FILTER_ADD_REDUNDANT,
    // # of data blocks redundantly inserted into block cache.
    // REQUIRES: BLOCK_CACHE_DATA_ADD_REDUNDANT <= BLOCK_CACHE_DATA_ADD
    BLOCK_CACHE_DATA_ADD_REDUNDANT,
    // # of dict blocks redundantly inserted into block cache.
    // REQUIRES: BLOCK_CACHE_COMPRESSION_DICT_ADD_REDUNDANT
    //           <= BLOCK_CACHE_COMPRESSION_DICT_ADD
    BLOCK_CACHE_COMPRESSION_DICT_ADD_REDUNDANT,

    // Secondary cache statistics
    SECONDARY_CACHE_HITS,

    // Fine grained secondary cache stats
    SECONDARY_CACHE_FILTER_HITS,
    SECONDARY_CACHE_INDEX_HITS,
    SECONDARY_CACHE_DATA_HITS,

    // Compressed secondary cache related stats
    COMPRESSED_SECONDARY_CACHE_DUMMY_HITS,
    COMPRESSED_SECONDARY_CACHE_HITS,
    COMPRESSED_SECONDARY_CACHE_PROMOTIONS,
    COMPRESSED_SECONDARY_CACHE_PROMOTION_SKIPS,

    // # of times bloom filter has avoided file reads, i.e., negatives.
    BLOOM_FILTER_USEFUL,
    // # of times bloom FullFilter has not avoided the reads.
    BLOOM_FILTER_FULL_POSITIVE,
    // # of times bloom FullFilter has not avoided the reads and data actually
    // exist.
    BLOOM_FILTER_FULL_TRUE_POSITIVE,
    // Prefix filter stats when used for point lookups (Get / MultiGet).
    // (For prefix filter stats on iterators, see *_LEVEL_SEEK_*.)
    // Checked: filter was queried
    BLOOM_FILTER_PREFIX_CHECKED,
    // Useful: filter returned false so prevented accessing data+index blocks
    BLOOM_FILTER_PREFIX_USEFUL,
    // True positive: found a key matching the point query. When another key
    // with the same prefix matches, it is considered a false positive by
    // these statistics even though the filter returned a true positive.
    BLOOM_FILTER_PREFIX_TRUE_POSITIVE,

    // # persistent cache hit
    PERSISTENT_CACHE_HIT,
    // # persistent cache miss
    PERSISTENT_CACHE_MISS,

    // # total simulation block cache hits
    SIM_BLOCK_CACHE_HIT,
    // # total simulation block cache misses
    SIM_BLOCK_CACHE_MISS,

    // # of memtable hits.
    MEMTABLE_HIT,
    // # of memtable misses.
    MEMTABLE_MISS,

    // # of Get() queries served by L0
    GET_HIT_L0,
    // # of Get() queries served by L1
    GET_HIT_L1,
    // # of Get() queries served by L2 and up
    GET_HIT_L2_AND_UP,

    /**
     * COMPACTION_KEY_DROP_* count the reasons for key drop during compaction
     * There are 4 reasons currently.
     */
    COMPACTION_KEY_DROP_NEWER_ENTRY,  // key was written with a newer value.
    // Also includes keys dropped for range del.
    COMPACTION_KEY_DROP_OBSOLETE,     // The key is obsolete.
    COMPACTION_KEY_DROP_RANGE_DEL,    // key was covered by a range tombstone.
    COMPACTION_KEY_DROP_USER,         // user compaction function has dropped the key.
    COMPACTION_RANGE_DEL_DROP_OBSOLETE,  // all keys in range were deleted.
    // Deletions obsoleted before bottom level due to file gap optimization.
    COMPACTION_OPTIMIZED_DEL_DROP_OBSOLETE,
    // If a compaction was canceled in sfm to prevent ENOSPC
    COMPACTION_CANCELLED,

    // Number of keys written to the database via the Put and Write call's
    NUMBER_KEYS_WRITTEN,
    // Number of Keys read,
    NUMBER_KEYS_READ,
    // Number keys updated, if inplace update is enabled
    NUMBER_KEYS_UPDATED,
    // The number of uncompressed bytes issued by DB::Put(), DB::Delete(),
    // DB::Merge(), and DB::Write().
    BYTES_WRITTEN,
    // The number of uncompressed bytes read from DB::Get().  It could be
    // either from memtables, cache, or table files.
    // For the number of logical bytes read from DB::MultiGet(),
    // please use NUMBER_MULTIGET_BYTES_READ.
    BYTES_READ,
    // The number of calls to seek/next/prev
    NUMBER_DB_SEEK,
    NUMBER_DB_NEXT,
    NUMBER_DB_PREV,
    // The number of calls to seek/next/prev that returned data
    NUMBER_DB_SEEK_FOUND,
    NUMBER_DB_NEXT_FOUND,
    NUMBER_DB_PREV_FOUND,
    // The number of uncompressed bytes read from an iterator.
    // Includes size of key and value.
    ITER_BYTES_READ,
    // Number of internal keys skipped by Iterator
    NUMBER_ITER_SKIP,
    // Number of times we had to reseek inside an iteration to skip
    // over large number of keys with same userkey.
    NUMBER_OF_RESEEKS_IN_ITERATION,

    NO_ITERATOR_CREATED,  // number of iterators created
    NO_ITERATOR_DELETED,  // number of iterators deleted

    NO_FILE_OPENS,
    NO_FILE_ERRORS,
    // Writer has to wait for compaction or flush to finish.
    STALL_MICROS,
    // The wait time for db mutex.
    // Disabled by default. To enable it set stats level to kAll
    DB_MUTEX_WAIT_MICROS,

    // Number of MultiGet calls, keys read, and bytes read
    NUMBER_MULTIGET_CALLS,
    NUMBER_MULTIGET_KEYS_READ,
    NUMBER_MULTIGET_BYTES_READ,
    // Number of keys actually found in MultiGet calls (vs number requested by
    // caller)
    // NUMBER_MULTIGET_KEYS_READ gives the number requested by caller
    NUMBER_MULTIGET_KEYS_FOUND,

    NUMBER_MERGE_FAILURES,

    // Record the number of calls to GetUpdatesSince. Useful to keep track of
    // transaction log iterator refreshes
    GET_UPDATES_SINCE_CALLS,
    WAL_FILE_SYNCED,  // Number of times WAL sync is done
    WAL_FILE_BYTES,   // Number of bytes written to WAL

    // Writes can be processed by requesting thread or by the thread at the
    // head of the writers queue.
    WRITE_DONE_BY_SELF,
    WRITE_DONE_BY_OTHER,  // Equivalent to writes done for others
    WRITE_WITH_WAL,       // Number of Write calls that request WAL
    COMPACT_READ_BYTES,   // Bytes read during compaction
    COMPACT_WRITE_BYTES,  // Bytes written during compaction
    FLUSH_WRITE_BYTES,    // Bytes written during flush

    // Compaction read and write statistics broken down by CompactionReason
    COMPACT_READ_BYTES_MARKED,
    COMPACT_READ_BYTES_PERIODIC,
    COMPACT_READ_BYTES_TTL,
    COMPACT_WRITE_BYTES_MARKED,
    COMPACT_WRITE_BYTES_PERIODIC,
    COMPACT_WRITE_BYTES_TTL,

    // Number of table's properties loaded directly from file, without creating
    // table reader object.
    NUMBER_DIRECT_LOAD_TABLE_PROPERTIES,
    NUMBER_SUPERVERSION_ACQUIRES,
    NUMBER_SUPERVERSION_RELEASES,
    NUMBER_SUPERVERSION_CLEANUPS,

    // # of compressions/decompressions executed
    NUMBER_BLOCK_COMPRESSED,
    NUMBER_BLOCK_DECOMPRESSED,
//    NUMBER_BLOCK_NOT_COMPRESSED,

    // Number of input bytes (uncompressed) to compression for SST blocks that
    // are stored compressed.
    BYTES_COMPRESSED_FROM,
    // Number of output bytes (compressed) from compression for SST blocks that
    // are stored compressed.
    BYTES_COMPRESSED_TO,
    // Number of uncompressed bytes for SST blocks that are stored uncompressed
    // because compression type is kNoCompression, or some error case caused
    // compression not to run or produce an output. Index blocks are only counted
    // if enable_index_compression is true.
    BYTES_COMPRESSION_BYPASSED,
    // Number of input bytes (uncompressed) to compression for SST blocks that
    // are stored uncompressed because the compression result was rejected,
    // either because the ratio was not acceptable (see
    // CompressionOptions::max_compressed_bytes_per_kb) or found invalid by the
    // `verify_compression` option.
    BYTES_COMPRESSION_REJECTED,

    // Like BYTES_COMPRESSION_BYPASSED but counting number of blocks
    NUMBER_BLOCK_COMPRESSION_BYPASSED,
    // Like BYTES_COMPRESSION_REJECTED but counting number of blocks
    NUMBER_BLOCK_COMPRESSION_REJECTED,

    // Number of input bytes (compressed) to decompression in reading compressed
    // SST blocks from storage.
    BYTES_DECOMPRESSED_FROM,
    // Number of output bytes (uncompressed) from decompression in reading
    // compressed SST blocks from storage.
    BYTES_DECOMPRESSED_TO,

    // Tickers that record cumulative time.
    MERGE_OPERATION_TOTAL_TIME,
    FILTER_OPERATION_TOTAL_TIME,
    COMPACTION_CPU_TOTAL_TIME,

    // Row cache.
    ROW_CACHE_HIT,
    ROW_CACHE_MISS,

    // Read amplification statistics.
    // Read amplification can be calculated using this formula
    // (READ_AMP_TOTAL_READ_BYTES / READ_AMP_ESTIMATE_USEFUL_BYTES)
    //
    // REQUIRES: ReadOptions::read_amp_bytes_per_bit to be enabled
    READ_AMP_ESTIMATE_USEFUL_BYTES,  // Estimate of total bytes actually used.
    READ_AMP_TOTAL_READ_BYTES,       // Total size of loaded data blocks.

    // Number of refill intervals where rate limiter's bytes are fully consumed.
    NUMBER_RATE_LIMITER_DRAINS,

    // BlobDB specific stats
    // # of Put/PutTTL/PutUntil to BlobDB. Only applicable to legacy BlobDB.
    BLOB_DB_NUM_PUT,
    // # of Write to BlobDB. Only applicable to legacy BlobDB.
    BLOB_DB_NUM_WRITE,
    // # of Get to BlobDB. Only applicable to legacy BlobDB.
    BLOB_DB_NUM_GET,
    // # of MultiGet to BlobDB. Only applicable to legacy BlobDB.
    BLOB_DB_NUM_MULTIGET,
    // # of Seek/SeekToFirst/SeekToLast/SeekForPrev to BlobDB iterator. Only
    // applicable to legacy BlobDB.
    BLOB_DB_NUM_SEEK,
    // # of Next to BlobDB iterator. Only applicable to legacy BlobDB.
    BLOB_DB_NUM_NEXT,
    // # of Prev to BlobDB iterator. Only applicable to legacy BlobDB.
    BLOB_DB_NUM_PREV,
    // # of keys written to BlobDB. Only applicable to legacy BlobDB.
    BLOB_DB_NUM_KEYS_WRITTEN,
    // # of keys read from BlobDB. Only applicable to legacy BlobDB.
    BLOB_DB_NUM_KEYS_READ,
    // # of bytes (key + value) written to BlobDB. Only applicable to legacy
    // BlobDB.
    BLOB_DB_BYTES_WRITTEN,
    // # of bytes (keys + value) read from BlobDB. Only applicable to legacy
    // BlobDB.
    BLOB_DB_BYTES_READ,
    // # of keys written by BlobDB as non-TTL inlined value. Only applicable to
    // legacy BlobDB.
    BLOB_DB_WRITE_INLINED,
    // # of keys written by BlobDB as TTL inlined value. Only applicable to legacy
    // BlobDB.
    BLOB_DB_WRITE_INLINED_TTL,
    // # of keys written by BlobDB as non-TTL blob value. Only applicable to
    // legacy BlobDB.
    BLOB_DB_WRITE_BLOB,
    // # of keys written by BlobDB as TTL blob value. Only applicable to legacy
    // BlobDB.
    BLOB_DB_WRITE_BLOB_TTL,
    // # of bytes written to blob file.
    BLOB_DB_BLOB_FILE_BYTES_WRITTEN,
    // # of bytes read from blob file.
    BLOB_DB_BLOB_FILE_BYTES_READ,
    // # of times a blob files being synced.
    BLOB_DB_BLOB_FILE_SYNCED,
    // # of blob index evicted from base DB by BlobDB compaction filter because
    // of expiration. Only applicable to legacy BlobDB.
    BLOB_DB_BLOB_INDEX_EXPIRED_COUNT,
    // size of blob index evicted from base DB by BlobDB compaction filter
    // because of expiration. Only applicable to legacy BlobDB.
    BLOB_DB_BLOB_INDEX_EXPIRED_SIZE,
    // # of blob index evicted from base DB by BlobDB compaction filter because
    // of corresponding file deleted. Only applicable to legacy BlobDB.
    BLOB_DB_BLOB_INDEX_EVICTED_COUNT,
    // size of blob index evicted from base DB by BlobDB compaction filter
    // because of corresponding file deleted. Only applicable to legacy BlobDB.
    BLOB_DB_BLOB_INDEX_EVICTED_SIZE,
    // # of blob files that were obsoleted by garbage collection. Only applicable
    // to legacy BlobDB.
    BLOB_DB_GC_NUM_FILES,
    // # of blob files generated by garbage collection. Only applicable to legacy
    // BlobDB.
    BLOB_DB_GC_NUM_NEW_FILES,
    // # of BlobDB garbage collection failures. Only applicable to legacy BlobDB.
    BLOB_DB_GC_FAILURES,
    // # of keys relocated to new blob file by garbage collection.
    BLOB_DB_GC_NUM_KEYS_RELOCATED,
    // # of bytes relocated to new blob file by garbage collection.
    BLOB_DB_GC_BYTES_RELOCATED,
    // # of blob files evicted because of BlobDB is full. Only applicable to
    // legacy BlobDB.
    BLOB_DB_FIFO_NUM_FILES_EVICTED,
    // # of keys in the blob files evicted because of BlobDB is full. Only
    // applicable to legacy BlobDB.
    BLOB_DB_FIFO_NUM_KEYS_EVICTED,
    // # of bytes in the blob files evicted because of BlobDB is full. Only
    // applicable to legacy BlobDB.
    BLOB_DB_FIFO_BYTES_EVICTED,

    // Integrated BlobDB specific stats
    // # of times cache miss when accessing blob from blob cache.
    BLOB_DB_CACHE_MISS,
    // # of times cache hit when accessing blob from blob cache.
    BLOB_DB_CACHE_HIT,
    // # of data blocks added to blob cache.
    BLOB_DB_CACHE_ADD,
    // # of failures when adding blobs to blob cache.
    BLOB_DB_CACHE_ADD_FAILURES,
    // # of bytes read from blob cache.
    BLOB_DB_CACHE_BYTES_READ,
    // # of bytes written into blob cache.
    BLOB_DB_CACHE_BYTES_WRITE,

    // These counters indicate a performance issue in WritePrepared transactions.
    // We should not seem them ticking them much.
    // # of times prepare_mutex_ is acquired in the fast path.
    TXN_PREPARE_MUTEX_OVERHEAD,
    // # of times old_commit_map_mutex_ is acquired in the fast path.
    TXN_OLD_COMMIT_MAP_MUTEX_OVERHEAD,
    // # of times we checked a batch for duplicate keys.
    TXN_DUPLICATE_KEY_OVERHEAD,
    // # of times snapshot_mutex_ is acquired in the fast path.
    TXN_SNAPSHOT_MUTEX_OVERHEAD,
    // # of times ::Get returned TryAgain due to expired snapshot seq
    TXN_GET_TRY_AGAIN,

    // # of files marked as trash by sst file manager and will be deleted
    // later by background thread.
    FILES_MARKED_TRASH,
    // # of trash files deleted by the background thread from the trash queue.
    FILES_DELETED_FROM_TRASH_QUEUE,
    // # of files deleted immediately by sst file manager through delete
    // scheduler.
    FILES_DELETED_IMMEDIATELY,

    // The counters for error handler, note that, bg_io_error is the subset of
    // bg_error and bg_retryable_io_error is the subset of bg_io_error.
    ERROR_HANDLER_BG_ERROR_COUNT,
    ERROR_HANDLER_BG_IO_ERROR_COUNT,
    ERROR_HANDLER_BG_RETRYABLE_IO_ERROR_COUNT,
    ERROR_HANDLER_AUTORESUME_COUNT,
    ERROR_HANDLER_AUTORESUME_RETRY_TOTAL_COUNT,
    ERROR_HANDLER_AUTORESUME_SUCCESS_COUNT,

    // Statistics for memtable garbage collection:
    // Raw bytes of data (payload) present on memtable at flush time.
    MEMTABLE_PAYLOAD_BYTES_AT_FLUSH,
    // Outdated bytes of data present on memtable at flush time.
    MEMTABLE_GARBAGE_BYTES_AT_FLUSH,

    // Bytes read by `VerifyChecksum()` and `VerifyFileChecksums()` APIs.
    VERIFY_CHECKSUM_READ_BYTES,

    // Bytes read/written while creating backups
    BACKUP_READ_BYTES,
    BACKUP_WRITE_BYTES,

    // Remote compaction read/write statistics
    REMOTE_COMPACT_READ_BYTES,
    REMOTE_COMPACT_WRITE_BYTES,

    // Tiered storage related statistics
    HOT_FILE_READ_BYTES,
    WARM_FILE_READ_BYTES,
    COLD_FILE_READ_BYTES,
    HOT_FILE_READ_COUNT,
    WARM_FILE_READ_COUNT,
    COLD_FILE_READ_COUNT,

    // Last level and non-last level read statistics
    LAST_LEVEL_READ_BYTES,
    LAST_LEVEL_READ_COUNT,
    NON_LAST_LEVEL_READ_BYTES,
    NON_LAST_LEVEL_READ_COUNT,

    // Statistics on iterator Seek() (and variants) for each sorted run. I.e. a
    // single user Seek() can result in many sorted run Seek()s.
    // The stats are split between last level and non-last level.
    // Filtered: a filter such as prefix Bloom filter indicate the Seek() would
    // not find anything relevant, so avoided a likely access to data+index
    // blocks.
    LAST_LEVEL_SEEK_FILTERED,
    // Filter match: a filter such as prefix Bloom filter was queried but did
    // not filter out the seek.
    LAST_LEVEL_SEEK_FILTER_MATCH,
    // At least one data block was accessed for a Seek() (or variant) on a
    // sorted run.
    LAST_LEVEL_SEEK_DATA,
    // At least one value() was accessed for the seek (suggesting it was useful),
    // and no filter such as prefix Bloom was queried.
    LAST_LEVEL_SEEK_DATA_USEFUL_NO_FILTER,
    // At least one value() was accessed for the seek (suggesting it was useful),
    // after querying a filter such as prefix Bloom.
    LAST_LEVEL_SEEK_DATA_USEFUL_FILTER_MATCH,
    // The same set of stats, but for non-last level seeks.
    NON_LAST_LEVEL_SEEK_FILTERED,
    NON_LAST_LEVEL_SEEK_FILTER_MATCH,
    NON_LAST_LEVEL_SEEK_DATA,
    NON_LAST_LEVEL_SEEK_DATA_USEFUL_NO_FILTER,
    NON_LAST_LEVEL_SEEK_DATA_USEFUL_FILTER_MATCH,

    // Number of block checksum verifications
    BLOCK_CHECKSUM_COMPUTE_COUNT,
    // Number of times RocksDB detected a corruption while verifying a block
    // checksum. RocksDB does not remember corruptions that happened during user
    // reads so the same block corruption may be detected multiple times.
    BLOCK_CHECKSUM_MISMATCH_COUNT,

    MULTIGET_COROUTINE_COUNT,

    // Time spent in the ReadAsync file system call
    READ_ASYNC_MICROS,
    // Number of errors returned to the async read callback
    ASYNC_READ_ERROR_COUNT,

    // Number of lookup into the prefetched tail (see
    // `TABLE_OPEN_PREFETCH_TAIL_READ_BYTES`)
    // that can't find its data for table open
    TABLE_OPEN_PREFETCH_TAIL_MISS,
    // Number of lookup into the prefetched tail (see
    // `TABLE_OPEN_PREFETCH_TAIL_READ_BYTES`)
    // that finds its data for table open
    TABLE_OPEN_PREFETCH_TAIL_HIT,

    // Statistics on the filtering by user-defined timestamps
    // # of times timestamps are checked on accessing the table
    TIMESTAMP_FILTER_TABLE_CHECKED,
    // # of times timestamps can successfully help skip the table access
    TIMESTAMP_FILTER_TABLE_FILTERED,

    // Number of times readahead is trimmed during scans when
    // ReadOptions.auto_readahead_size is set.
    READAHEAD_TRIMMED,

    // Number of FIFO compactions that drop files based on different reasons
    FIFO_MAX_SIZE_COMPACTIONS,
    FIFO_TTL_COMPACTIONS,

    // Number of bytes prefetched during user initiated scan
    PREFETCH_BYTES,

    // Number of prefetched bytes that were actually useful
    PREFETCH_BYTES_USEFUL,

    // Number of FS reads avoided due to scan prefetching
    PREFETCH_HITS,

    // Footer corruption detected when opening an SST file for reading
    SST_FOOTER_CORRUPTION_COUNT,

    // Counters for file read retries with the verify_and_reconstruct_read
    // file system option after detecting a checksum mismatch
    FILE_READ_CORRUPTION_RETRY_COUNT,
    FILE_READ_CORRUPTION_RETRY_SUCCESS_COUNT,

    TICKER_ENUM_MAX;
}
