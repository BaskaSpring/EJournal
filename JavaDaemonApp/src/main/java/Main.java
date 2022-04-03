import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static class Computer {
        String name;
        ArrayList<Disc> discArrayList;

        public Computer(String name, ArrayList<Disc> discArrayList) {
            this.name = name;
            this.discArrayList = discArrayList;
        }

        public Computer() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Disc> getDiscArrayList() {
            return discArrayList;
        }

        public void setDiscArrayList(ArrayList<Disc> discArrayList) {
            this.discArrayList = discArrayList;
        }
    }

    public static class Disc {

        String name;
        String freeSpace;
        String totalSpace;

        public Disc(String name, String freeSpace, String totalSpace) {
            this.name = name;
            this.freeSpace = freeSpace;
            this.totalSpace = totalSpace;
        }

        public Disc() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFreeSpace() {
            return freeSpace;
        }

        public void setFreeSpace(String freeSpace) {
            this.freeSpace = freeSpace;
        }

        public String getTotalSpace() {
            return totalSpace;
        }

        public void setTotalSpace(String totalSpace) {
            this.totalSpace = totalSpace;
        }
    }

    public static class PathPayload{
        List<String> path;
        Integer refreshTime;

        public PathPayload(List<String> path, Integer refreshTime) {
            this.path = path;
            this.refreshTime = refreshTime;
        }

        public PathPayload() {
        }

        public List<String> getPath() {
            return path;
        }

        public void setPath(List<String> path) {
            this.path = path;
        }

        public Integer getRefreshTime() {
            return refreshTime;
        }

        public void setRefreshTime(Integer refreshTime) {
            this.refreshTime = refreshTime;
        }
    }

    public static class _Files{
        String fileName;
        Long createDate;

        public _Files(String fileName, Long createDate) {
            this.fileName = fileName;
            this.createDate = createDate;
        }

        public _Files() {
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public Long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Long createDate) {
            this.createDate = createDate;
        }
    }

    public static class FilesPayload{
        String serverName;
        List<_Files> filesList;

        public FilesPayload(String serverName, List<_Files> filesList) {
            this.serverName = serverName;
            this.filesList = filesList;
        }

        public FilesPayload() {
        }

        public String getServerName() {
            return serverName;
        }

        public void setServerName(String serverName) {
            this.serverName = serverName;
        }

        public List<_Files> getFilesList() {
            return filesList;
        }

        public void setFilesList(List<_Files> filesList) {
            this.filesList = filesList;
        }
    }

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(950))
            .build();

    public static HttpResponse<String> response;
    public static HttpRequest request;

    public static void main(String[] args) throws UnknownHostException {

        Thread DiscObs = new Thread(new Runnable() {
            @Override
            public void run() {
                String compName = null;
                try {
                    compName = InetAddress.getLocalHost().getHostName();
                } catch (UnknownHostException ignored) {
                }
                while (true) {
                    try {
                        ArrayList<Disc> discArrayList = new ArrayList<>();
                        Stream.of(File.listRoots()).forEach(f -> discArrayList.add(new Disc(f.getAbsolutePath(),Long.toString(f.getFreeSpace()),Long.toString(f.getTotalSpace()))));
                        Computer computer = new Computer(compName,discArrayList);
                        Gson gson = new Gson();
                        String Payload = gson.toJson(computer);
                        request = HttpRequest.newBuilder()
                                .POST(HttpRequest.BodyPublishers.ofString(Payload))
                                .uri(URI.create("http://127.0.0.1:8001/apid/v1/discspace"))
                                .version(HttpClient.Version.HTTP_2)
                                .header("Content-Type", "application/json")
                                .build();
                        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode()==200) {
                            String json = response.body();
                            int time = Integer.parseInt(json);
                            if (time == 0) {
                                break;
                            }
                            Thread.sleep(time * 1000);
                        } else {
                            Thread.sleep(600 * 1000);
                        }
                    } catch (InterruptedException | IOException ignored) {
                    }
                }
            }
        });

        Thread FilesObs = new Thread(new Runnable() {
            @Override
            public void run() {
                String compName = null;
                try {
                    compName = InetAddress.getLocalHost().getHostName();
                } catch (UnknownHostException ignored) {
                }
                while (true) {
                    try {
                        request = HttpRequest.newBuilder()
                                .GET()
                                .uri(URI.create("http://127.0.0.1:8001/apid/v1/files?computerName="+compName))
                                .version(HttpClient.Version.HTTP_2)
                                .build();
                        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode()==200) {
                            String json = response.body();
                            if (json.equals("0")) {
                                break;
                            }
                            Gson gson = new Gson();
                            PathPayload pathPayload = gson.fromJson(json,PathPayload.class);
                            List<_Files> filesList = new ArrayList<>();
                            pathPayload.getPath().parallelStream().forEach(x-> {
                                try {
                                    Files.newDirectoryStream(Paths.get(x)).forEach(y-> {
                                         if (!y.toFile().isDirectory()){
                                             _Files files = new _Files();
                                             files.setFileName(y.getFileName().toString());
                                             try {
                                                 FileTime creationTime = (FileTime) Files.getAttribute(y, "creationTime");
                                                 files.setCreateDate(creationTime.toInstant().getEpochSecond());
                                             } catch (IOException e) {
                                                 e.printStackTrace();
                                             }
                                             filesList.add(files);
                                         }
                                    });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            FilesPayload filesPayload = new FilesPayload();
                            filesPayload.setFilesList(filesList);
                            filesPayload.setServerName(compName);
                            String Payload = gson.toJson(filesPayload);
                            request = HttpRequest.newBuilder()
                                    .POST(HttpRequest.BodyPublishers.ofString(Payload))
                                    .uri(URI.create("http://127.0.0.1:8001/apid/v1/files"))
                                    .version(HttpClient.Version.HTTP_2)
                                    .header("Content-Type", "application/json")
                                    .build();
                            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                            Thread.sleep( pathPayload.getRefreshTime() * 1000);
                        } else {
                            Thread.sleep(600 * 1000);
                        }
                    } catch (InterruptedException | IOException ignored) {
                    }
                }
            }
        });
        FilesObs.start();
        DiscObs.start();
    }
}
