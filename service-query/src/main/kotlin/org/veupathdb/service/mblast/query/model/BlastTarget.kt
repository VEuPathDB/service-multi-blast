package org.veupathdb.service.mblast.query.model

/**
 * # BLAST Target Database
 *
 * Represents a target database for a BLAST query.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  2.0.0
 *
 * @constructor Constructs a new `BlastTarget` instance.
 *
 * @param displayName Display name / target category name.  Typically, the name
 * of an organism.
 *
 * @param fileName Name of the blast database (without a file extension).
 */
data class BlastTarget(val displayName: String, val fileName: String)