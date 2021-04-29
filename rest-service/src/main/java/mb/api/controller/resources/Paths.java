package mb.api.controller.resources;

class Paths
{
  final static String Jobs       = "/jobs";
  final static String JobByID    = Jobs + "/{" + Vars.JobID + "}";
  final static String Reports    = "/reports";
  final static String ReportByID = Reports + "/{" + Vars.ReportID + "}";
  final static String ReportData = ReportByID + "/{" + Vars.FileName + "}";
}
