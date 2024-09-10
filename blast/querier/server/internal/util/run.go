package util

import (
	"os/exec"

	"github.com/sirupsen/logrus"
)

func RunCommand(cmd *exec.Cmd, log *logrus.Entry) error {
	if err := cmd.Start(); err != nil {
		return err
	}

	log.Debugf("Process ID: %d", cmd.Process.Pid)

	return cmd.Wait()
}
