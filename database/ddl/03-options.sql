CREATE TABLE tool_options
(
  option_id SMALLSERIAL PRIMARY KEY NOT NULL
  , name      VARCHAR(16) NOT NULL UNIQUE
);

INSERT INTO
  tool_options (name)
VALUES
  ('best_hit_overhang')
, ('best_hit_score_edge')
, ('comp_based_stats')
, ('culling_limit')
, ('db')                          -- 5
, ('db_gencode')
, ('db_hard_mask')
, ('db_soft_mask')
, ('dbsize')
, ('dust')                        -- 10
, ('entrez_query')
, ('evalue')
, ('export_search_strategy')
, ('filtering_db')
, ('gap_trigger')                 -- 15
, ('gapextend')
, ('gapopen')
, ('gilist')
, ('ignore_msa_master')
, ('import_search_strategy')      -- 20
, ('in_msa')
, ('in_pssm')
, ('inclusion_ethresh')
, ('index_name')
, ('ipglist')                     -- 25
, ('lcase_masking')
, ('line_length')
, ('matrix')
, ('max_hsps')
, ('max_intron_length')           -- 30
, ('max_target_seqs')
, ('min_raw_gapped_score')
, ('msa_master_idx')
, ('negative_gilist')
, ('negative_ipglist')            -- 35
, ('negative_seqidlist')
, ('negative_taxidlist')
, ('negative_taxids')
, ('no_greedy')
, ('num_alignments')              -- 40
, ('num_descriptions')
, ('num_iterations')
, ('num_threads')
, ('off_diagonal_range')
, ('out')                         -- 45
, ('out_ascii_pssm')
, ('out_pssm')
, ('outfmt')
, ('parse_deflines')
, ('penalty')                     -- 50
, ('perc_identity')
, ('phi_pattern')
, ('pseudocount')
, ('qcov_hsp_perc')
, ('query')                       -- 55
, ('query_gencode')
, ('query_loc')
, ('remote')
, ('reward')
, ('save_ascii_pssm')             -- 60
, ('save_each_pssm')
, ('save_pssm_after_last_round')
, ('searchsp')
, ('seg')
, ('seqidlist')                   -- 65
, ('show_gis')
, ('soft_masking')
, ('sorthits')
, ('sorthsps')
, ('strand')                      -- 70
, ('subject')
, ('subject_besthit')
, ('subject_loc')
, ('sum_stats')
, ('task')                        -- 75
, ('taxidlist')
, ('taxids')
, ('template_length')
, ('template_type')
, ('threshold')                   -- 80
, ('ungapped')
, ('use_index')
, ('use_sw_tback')
, ('version')
, ('window_masker_db')            -- 85
, ('window_masker_taxid')
, ('window_size')
, ('word_size')
, ('xdrop_gap')
, ('xdrop_gap_final')             -- 90
, ('xdrop_ungap')
;
