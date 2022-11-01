package org.veupathdb.lib.mblast.sdk.query.http

/**
 * Index of all the BLAST-able targets available to the MultiBlast service.
 *
 * The index is a nested map structure where the first level key is the name of
 * a target site from which a group of BLAST-able targets belong.  The next
 * level key is the display name of a BLAST-able target, and the inner value is
 * two collections of BLAST database names, nucleotide targets and protein
 * targets.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
class MultiBlastTargetIndex : HashMap<TargetSite, BlastTargets>()

