package mblast

import java.nio.file.Paths
import kotlin.io.path.exists
import kotlin.io.path.pathString

/**
 * Blast Database Path Provider
 *
 * Provides functionality for testing the existence of and getting the raw paths
 * to target Blast databases on the filesystem.
 *
 * @author Elizabeth Harper - https://github.com/Foxcapades
 * @since 2.0.0
 */
class BlastDBProvider(
  private val root: String,
  private val build: String,
) {

  /**
   * Tests whether a nucleotide or protein Blast DB exists matching the given
   * parameters.
   *
   * @param site VEuPathDB site housing the Blast DB.
   *
   * Example: `PlasmoDB`
   *
   * @param organism Target organism for the Blast DB.
   *
   * Example: `Pfalciparum3D7`
   *
   * @param target Database target for the Blast DB.
   *
   * Example: `Pfalciparum3D7AnnotatedTranscripts`
   *
   * @return `true` if the given parameters match either a nucleotide or protein
   * database that exists on the filesystem, otherwise returns `false`.
   */
  fun dbExists(site: String, organism: String, target: String) =
    nucleotideDBExists(site, organism, target)
      || proteinDBExists(site, organism, target)

  /**
   * Tests whether a nucleotide Blast DB exists matching the given parameters.
   *
   * @param site VEuPathDB site housing the Blast DB.
   *
   * Example: `PlasmoDB`
   *
   * @param organism Target organism for the Blast DB.
   *
   * Example: `Pfalciparum3D7`
   *
   * @param target Database target for the Blast DB.
   *
   * Example: `Pfalciparum3D7AnnotatedTranscripts`
   *
   * @return `true` if the given parameters match a nucleotide database that
   * exists on the filesystem, otherwise returns `false`.
   */
  fun nucleotideDBExists(site: String, organism: String, target: String) =
    Paths.get(root, site, build, organism, "$target.nin").exists()

  /**
   * Tests whether a protein Blast DB exists matching the given parameters.
   *
   * @param site VEuPathDB site housing the Blast DB.
   *
   * Example: `PlasmoDB`
   *
   * @param organism Target organism for the Blast DB.
   *
   * Example: `Pfalciparum3D7`
   *
   * @param target Database target for the Blast DB.
   *
   * Example: `Pfalciparum3D7AnnotatedProteins`
   *
   * @return `true` if the given parameters match a protein database that
   * exists on the filesystem, otherwise returns `false`.
   */
  fun proteinDBExists(site: String, organism: String, target: String) =
    Paths.get(root, site, build, organism, "$target.pin").exists()

  /**
   * Generates a database path to the target database matching the given
   * parameters.
   *
   * **NOTE** The path returned by this method likely does not point to a real
   * file that actually exists on the system.  Instead, this path represents a
   * collection of files with different extensions that make up a Blast DB.
   *
   * This method makes no guarantee that the returned path actually points to
   * a valid Blast DB.
   *
   * Testing for the existence of the database can be done with the [dbExists]
   * method.
   *
   * @param site VEuPathDB site housing the Blast DB.
   *
   * Example: `PlasmoDB`
   *
   * @param organism Target organism for the Blast DB.
   *
   * Example: `Pfalciparum3D7`
   *
   * @param target Database target for the Blast DB.
   *
   * Example: `Pfalciparum3D7AnnotatedTranscripts`
   *
   * @return A path string for a Blast DB matching the given parameters.
   */
  fun dbPath(site: String, organism: String, target: String) =
    Paths.get(root, site, build, organism, target).pathString
}
