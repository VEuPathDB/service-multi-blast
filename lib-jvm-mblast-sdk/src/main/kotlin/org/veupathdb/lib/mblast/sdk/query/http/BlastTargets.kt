package org.veupathdb.lib.mblast.sdk.query.http

/**
 * Index oF BLAST-able targets available to a single site through the MultiBlast
 * service.
 *
 * The index is a map structure where the key is the display name of a
 * BLAST-able target, and the value is two collections of BLAST database names,
 * nucleotide targets, and protein targets.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
class BlastTargets : HashMap<String, BlastDatabases>()