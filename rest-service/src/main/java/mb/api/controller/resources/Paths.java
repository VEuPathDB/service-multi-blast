package mb.api.controller.resources;

class Paths
{
  final static String Jobs        = "/jobs";
  final static String JobByID     = "/{" + Vars.JobID + "}";
  final static String JobQuery    = JobByID + "/query";
  final static String Reports     = "/reports";
  final static String ReportByID  = "/{" + Vars.ReportID + "}";
  final static String ReportFiles = ReportByID + "/files";
  final static String ReportData  = ReportFiles + "/{" + Vars.FileName + "}";
}
