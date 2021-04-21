package blast_cli

type BlastTool string

const (
	BlastToolBlastN     BlastTool = "blastn"
	BlastToolBlastP     BlastTool = "blastp"
	BlastToolBlastX     BlastTool = "blastx"
	BlastToolDeltaBlast BlastTool = "deltablast"
	BlastToolPSIBlast   BlastTool = "psiblast"
	BlastToolRPSBlast   BlastTool = "rpsblast"
	BlastToolRPSTBlastN BlastTool = "rpstblastn"
	BlastToolTBlastN    BlastTool = "tblastn"
	BlastToolTBlastX    BlastTool = "tblastx"
)
