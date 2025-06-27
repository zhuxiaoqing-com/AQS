#include <iostream>
#include "DetourNavMesh.h"
#include "DetourNavMeshQuery.h"
#include "DetourAlloc.h"
#include "memory.h"
using namespace std;
#include <time.h>
#include <assert.h>

int THREAD_COUNT = 8;
int PER_COUNT = 10000000;

//测试111
struct NavMeshSetHeader
{
	int magic;
	int version;
	int numTiles;
	dtNavMeshParams params;
};

struct NavMeshTileHeader
{
	dtTileRef tileRef;
	int dataSize;
};

static const int NAVMESHSET_MAGIC = 'M' << 24 | 'S' << 16 | 'E' << 8 | 'T'; //'MSET';
//static const int NAVMESHSET_MAGIC = 1414743380;
static const int NAVMESHSET_VERSION = 1;

dtNavMesh* loadAll(const char* path)
{
	FILE* fp = fopen(path, "rb");
	printf("open fail errno = %d  \n", errno);
	if (!fp) return 0;

	// Read header.
	NavMeshSetHeader header;
	size_t readLen = fread(&header, sizeof(NavMeshSetHeader), 1, fp);
	if (readLen != 1)
	{
		cout << "err 1 " << endl;
		fclose(fp);
		return 0;
	}
	if (header.magic != NAVMESHSET_MAGIC)
	{
		cout << "err 2 " << endl;
		fclose(fp);
		return 0;
	}
	if (header.version != NAVMESHSET_VERSION)
	{
		cout << "err 3 " << endl;
		fclose(fp);
		return 0;
	}

	dtNavMesh* mesh = dtAllocNavMesh();
	if (!mesh)
	{
		cout << "err 4 " << endl;
		fclose(fp);
		return 0;
	}
	dtStatus status = mesh->init(&header.params);
	if (dtStatusFailed(status))
	{
		cout << "err 5 " << endl;
		fclose(fp);
		return 0;
	}

	// Read tiles.
	for (int i = 0; i < header.numTiles; ++i)
	{
		NavMeshTileHeader tileHeader;
		readLen = fread(&tileHeader, sizeof(tileHeader), 1, fp);
		if (readLen != 1)
			return 0;

		if (!tileHeader.tileRef || !tileHeader.dataSize)
			break;

		unsigned char* data = (unsigned char*)dtAlloc(tileHeader.dataSize, DT_ALLOC_PERM);
		if (!data) break;
		memset(data, 0, tileHeader.dataSize);
		readLen = fread(data, tileHeader.dataSize, 1, fp);
		if (readLen != 1)
			return 0;

		mesh->addTile(data, tileHeader.dataSize, DT_TILE_FREE_DATA, tileHeader.tileRef, 0);
	}

	fclose(fp);

	return mesh;
}

dtNavMesh* navMesh;;

int quary() {
	//cout << "quary" << endl;
	//navData
	dtNavMeshQuery* navMeshQuery = dtAllocNavMeshQuery();
	navMeshQuery->init(navMesh, 1024);

	//quary

	//find
	float paths[3000];
	const float polyPickExt[3] = { 0.1f,1,0.1f };
	dtQueryFilter filter;
	float startPos[3] = { 54.299f, 0.12f, 71.999f };
	//float startPos[3] = { 134.299f, 0.12f, 133.299f };
	float endPos[3] = { 54.467f,0.12f, 72.161f };
	float* start = startPos;
	float* end = endPos;

	dtPolyRef startRef;
	float nspos[3];
	navMeshQuery->findNearestPoly(start, polyPickExt, &filter, &startRef, nspos);

	dtPolyRef endRef;
	float nepos[3];
	navMeshQuery->findNearestPoly(end, polyPickExt, &filter, &endRef, nepos);

	int count = 0;
	dtPolyRef polys[1024];
	int npolys = 0;
	navMeshQuery->findPath(startRef, endRef, start, end, &filter, polys, &npolys, 1024);
	if (npolys) {
		navMeshQuery->findStraightPath(start, end, polys, npolys, paths, 0, 0, &count, 1024);
	}
	if (count <= 0) {
		cout << "error path!!! " << endl;
		//assert(false);
	}

	cout << "paths ";
	for (int i = 0; i < count * 3; i++) {
		cout << paths[i] << ",";
	}
	cout << endl;

	//dtFreeNavMeshQuery(navMeshQuery);

	


	//float startPos1[3] = { 1154.299f, 0.12f, 71111.999f };
	//start = startPos1;
	navMeshQuery->findNearestPoly(start, polyPickExt, &filter, &startRef, nspos);

	static const int MAX_RES = 32;
	dtPolyRef res[MAX_RES];
	float t, norm[3];
	int nres = 0;
	navMeshQuery->raycast(startRef, start, end, &filter, &t, norm, res, &nres, MAX_RES);

	cout << "raycast11 ";
	for (int i = 0; i <  3; i++) {
		cout << norm[i] << ",";
	}
	cout << endl;

	return 0;
}

void* loop(void* args) {
	for (int i = 0; i < PER_COUNT; i++)
		quary();
	return 0;
}


int main() {
	cout << "start" << endl;

	//mesh
	//navMesh = loadAll("D:\\src\\ServerNew\\GameStart\\config\nav\\CarrierEditor_tiles_tilecache.bytes");
	//navMesh = loadAll("E:\\recastc++\\all_tiles_tilecache.bin");
	navMesh = loadAll("E://recastc++//recastnavigation-master//recastnavigation-master//RecastDemo//Bin//all_tiles_navmesh.bin");
	//navMesh = loadAll("E:\\recastc++\\CarrierEditor_tiles_tilecache.bytes");
	if (!navMesh) {
		cout << "!navMesh" << endl;
		return 0;
	}

	quary();

	return 0;
}